package com.example.response;

import com.example.enums.error.ApplicationCode;
import com.example.enums.error.ErrorCode;
import com.example.enums.error.ValidationCode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

@Data
public final class Response<T> {
    private final static Logger LOGGER = LoggerFactory.getLogger(Response.class);

    private int count;

    private T result;

    private List<ErrorResponse> error;

    private String path;

    private Response() {
    }

    private Response(ErrorResponse error, String path) {
        this.error = Collections.singletonList(error);
        this.path = path;
    }

    private Response(List<ErrorResponse> error, String path) {
        this.error = error;
        this.path = path;
    }

    public static <T> Response<T> createResponse(final T model) {
        Response<T> response = new Response<>();
        if (model instanceof Collection collection) {
            response.count = collection.size();
        } else if (model instanceof Map map) {
            response.count = map.size();
        } else {
            response.count = 1;
        }
        response.result = model;
        return response;
    }

    public static <O> Response<O> createCountResponse(final int count) {
        Response<O> response = new Response<>();
        response.count = count;
        return response;
    }

    public static <O> Response<O> createErrorResponse(String message, String detailMessage, String path) {
        ErrorCode code = ApplicationCode.UNHANDLED_EXCEPTION;
        String msg = LocalizationHelper.getLocalizedMessage(code, new Object());
        Response<O> response = new Response<>(new ErrorResponse(code.getCode(),
                msg, null, StringUtils.join(message, " - ",detailMessage)), path);
        LOGGER.error("Error Response :: {}", response);
        return response;
    }

    public static <O> Response<O> createErrorResponse(final ErrorCode errorCode, String detailMessage, Object[] params, String path) {
        String message = LocalizationHelper.getLocalizedMessage(errorCode, params);
        Response<O> response = new Response<>(new ErrorResponse(errorCode.getCode(), message, Arrays.asList(params), detailMessage),
                path);
        LOGGER.error("Error Response :: {}", response);
        return response;
    }

    public static Response<String> createErrorResponse(final Map<String, ValidationCode> validationError, String path) {
        List<ErrorResponse> errorResponses = validationError.keySet()
                .parallelStream()
                .map(key -> {
                    ValidationCode validation = validationError.get(key);
                    String message = LocalizationHelper.getLocalizedMessage(validation, key);
                    return new ErrorResponse(validation.getCode(), message, Collections.singletonList(key), null);
                })
                .collect(Collectors.toList());

        Response<String> response = new Response<>(errorResponses, path);
        LOGGER.error("Error Response :: {}", response);
        return response;
    }

    public static Response<String> createErrorResponse(final List<FieldError> fieldErrors, String path) {
        List<ErrorResponse> errorResponses = fieldErrors.parallelStream()
                .map(error -> {
                    //Get validation error code, if we have given message with specific error code
                    //eg. @NotBlank(message="E1000")
                    ValidationCode validation = ValidationCode.lookup(error.getDefaultMessage());

                    if (validation == null) {
                        //Get validation error code based on type of validation @NotBlank, @Min, @Max etc.
                        //FieldError getCode() return type of annotation
                        validation = ValidationCode.lookupType(error.getCode());
                    }
                    String message = LocalizationHelper.getLocalizedMessage(validation, error.getArguments());

                    List<Object> param = new ArrayList<>();
                    param.add(error.getField());
                    Arrays.asList(error.getArguments())
                            .forEach(value -> {
                                if(value instanceof Integer || value instanceof Long) {
                                    param.add(value);
                                }
                            });
                    return new ErrorResponse(validation.getCode(), message, param, null);
                })
                .collect(Collectors.toList());

        Response<String> response = new Response<>(errorResponses, path);
        LOGGER.error("Error Response :: {}", response);
        return response;
    }
}

@Slf4j
@Component
class LocalizationHelper {
    private static MessageSource messageSource;

    LocalizationHelper(MessageSource messageSource) {
        LocalizationHelper.messageSource = messageSource;
    }

    static String getLocalizedMessage(ErrorCode errorCode, Object... params) {
        String message;
        try {
            message = messageSource.getMessage(errorCode.getCode(), params, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            log.error("No message found for {} in resource bundle...", errorCode.getCode());
            message = MessageFormat.format(errorCode.getDescription(), params);
        }
        return message;
    }
}