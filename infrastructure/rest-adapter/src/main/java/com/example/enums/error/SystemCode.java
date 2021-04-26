package com.example.enums.error;

public enum SystemCode implements ErrorCode{
    ILLEGAL_STATE("E1001", "IllegalStateException thrown by application."),
    DATA_INTEGRITY_VIOLATION("E1001", "DataIntegrityViolationException thrown by application."),
    UNSUPPORTED_ENCODING("E1001", "UnsupportedEncodingException thrown by application."),
    AMAZON_CLIENT("E1004", "Request was not correctly transmitted to the service."),
    NO_RESULT("E1005", "No result found."),
    FILE_SIZE_LIMIT_EXCEEDED("E1006", "File size exceeded maximum limit of {} MB."),
    ACCESS_DENIED("E1007", "User don't have access to resource."),
    SENDING_NOTIFICATION_ERROR("E1008", "Error occurred while sending notification."),
    CONSTRAINT_VIOLATION_EXCEPTION("E1009", "Database {} Constraint violation.")
    ;

    private final String code;
    private final String description;

    SystemCode(String number, String description) {
        this.code = number;
        this.description = description;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
