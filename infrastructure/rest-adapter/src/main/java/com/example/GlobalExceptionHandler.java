package com.example;


import com.example.enums.error.SystemCode;
import com.example.enums.error.ValidationCode;
import com.example.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;
import java.io.UnsupportedEncodingException;
import java.nio.file.AccessDeniedException;
import java.time.format.DateTimeParseException;
import java.util.List;


@ControllerAdvice
@Slf4j
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e, WebRequest request) {
        Response response = Response.createErrorResponse(SystemCode.CONSTRAINT_VIOLATION_EXCEPTION, e.getMessage(),
                new String[]{e.getMessage()}, request.getDescription(false));
        log.error("SQL error Constraint Violation Exception :: ", e);
        return handleExceptionInternal(e, response, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException e, WebRequest request) {
        Response response = Response.createErrorResponse(SystemCode.ILLEGAL_STATE, e.getMessage(),
                null, request.getDescription(false));
        log.error("Illegal Argument Exception :: ", e);
        return handleExceptionInternal(e, response, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = UnsupportedEncodingException.class)
    protected ResponseEntity<Object> handleUnsupportedEncodingException(UnsupportedEncodingException e, WebRequest request) {
        Response response = Response.createErrorResponse(SystemCode.UNSUPPORTED_ENCODING, e.getMessage(),
                null, request.getDescription(false));
        log.error("Unsupported Encoding Exception :: ", e);
        return handleExceptionInternal(e, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = MultipartException.class)
    protected ResponseEntity<Object> handleFileSizeLimitExceededException(MultipartException e, WebRequest request) {
        Response response = Response.createErrorResponse(SystemCode.FILE_SIZE_LIMIT_EXCEEDED, e.getMessage(),
                null, request.getDescription(false));
        log.error("Error while uploading file :: ", e);
        return handleExceptionInternal(e, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value =  DateTimeParseException.class )
    protected ResponseEntity<Object> handleDateTimeParseException(DateTimeParseException e, WebRequest request) {
        Response response = Response.createErrorResponse(ValidationCode.INVALID_DATE, null, new String[] { e.getParsedString() }
                , request.getDescription(false));
        log.error("Error while parsing date :: ", e);
        return handleExceptionInternal(e, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value =  AccessDeniedException.class )
    protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException e, WebRequest request) {
        Response response = Response.createErrorResponse(SystemCode.ACCESS_DENIED, e.getMessage(), null
                , request.getDescription(false));
        log.error("Invalid access :: ", e);
        return handleExceptionInternal(e, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Response response = null;
        if(ex.getCause() != null && ex.getCause().getCause() instanceof DateTimeParseException) {
            String dateValue = ((DateTimeParseException) ex.getCause().getCause()).getParsedString();
            response = Response.createErrorResponse(ValidationCode.INVALID_DATE, null, new String[] { dateValue }
                    , request.getDescription(false));
        }
        log.error("Error while parsing request :: ", ex);
        return handleExceptionInternal(ex, response, headers, status, request);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        Response response = Response.createErrorResponse(fieldErrors, null);
        log.error("Error while validating request.", e);
        return handleExceptionInternal(e, response, headers, status, request);
    }
}
