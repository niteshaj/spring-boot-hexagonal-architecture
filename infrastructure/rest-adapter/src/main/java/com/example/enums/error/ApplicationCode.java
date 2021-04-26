package com.example.enums.error;

public enum ApplicationCode implements ErrorCode{
    UNHANDLED_EXCEPTION("E5000", "Error occurred while processing your request. Please try after some time or contact admin."),

    NO_TOKEN("E5001", "Please provide token."),
    INVALID_TOKEN("E5002", "Invalid token."),

    INVALID_ACTIVATION_LINK("E5003", "Invalid activation link. Please contact admin for further details."),
    EXPIRED_ACTIVATION_LINK("E5004", "Verification link expired."),
    ALREADY_ACTIVATE("E5005", "User is already activated or deleted. Please contact admin for further details."),
    ACTIVATE_SENT_TIME_IS_NULL("E5006", "Unable to find valid activation link. Please contact admin for further details."),
    PASSWORD_IS_REQUIRED("E5007", "Please provide password to reset."),
    LOGIN_NAME_IS_REQUIRED("E5008", "Please provide valid login name."),

    UNAUTHORISED_CUSTOMER("E5011", "This user is not authorised to create a Customers."),
    INACTIVE_TENANT("E5012", "Your are not able to login because your company is inactive. Please contact admin."),
    INVALID_TENANT_ID("E5013", "Tenant with id does't exist."),

    INVALID_LOGIN_NAME("E5014", "User with login name does't exist."),
    INVALID_USER_ID("E5015", "User with login id does't exist."),
    INACTIVE_USER("E5016", "Your are not able to login because your account is inactive. Please contact admin."),
    USER_NOT_IN_CONTEXT("E5017", "Cannot persist a TransactionalEntity without a user in the RequestContext for this thread."),
    INVALID_PIN("E5018", "Invalid pin number."),

    USER_COUNT_REACHED("E5019", "Your limit of {0} users is reached."),
    SUBSCRIPTION_NOT_STARTED("E5020", "Subscription for user {0} is will start from {0}."),
    SUBSCRIPTION_ENDED("E5021", "Subscription for user {0} is ended on {1}."),
    SMS_COUNT_REACHED("E5022", "Company is not opted for SMS service."),
    WORKER_COUNT_REACHED("E5023", "Your limit of {0} worker is reached."),
    ALREADY_SELF_MANAGEMENT_MODE("E5024", "User already in self management mode."),
    NOT_IN_SELF_MANAGEMENT_MODE("E5025", "User is not in self management mode."),

    WORKER_DOES_NOT_EXIST("E5026", "Worker with id {0} does not exist"),
    WORKER_IDS_REQUIRED("E5027", "Please provide worker ids to get worker mobile number."),

    TRADE_MANDATORY("E5028","Worker must have at least one trade assigned."),
    TRADE_DOES_NOT_EXIST("E5029", "Trade with id {0} does not exist."),
    TRADE_WITH_NAME_EXIST("E5030", "Trade with name {0} already exist."),
    TRADE_IDS_REQUIRED("E5031", "Please provide trade ids to get worker mobile number."),

    COMPANY_MANDATORY("E5032","Company should be assign to Worker."),
    COMPANY_DOES_NOT_EXIST("E5033", "Company with id {0} does not exist."),
    COMPANY_WITH_NAME_EXIST("E5034", "Company with name {0} already exist."),
    COMPANY_IDS_REQUIRED("E5035", "Please provide company ids to get worker mobile number."),

    PAGE_NO_SIZE_INVALID("E5036", "Page number and size can't be negative."),
    INVALID_TYPE("E5037", "Please provide valid type for {0}."),
    INVALID_ID("E5038", "Please provide valid ids."),

    BLANK_FILE_EXTENSION("E5039", "Invalid file format."),
    INVALID_FILE_EXTENSION("E5040", "File extension {0} is invalid. Please provide valid file extension."),
    INVALID_FILE_NAME("E5041", "Please provide valid file name"),
    INVALID_UPLOAD_REQUEST("E5042", "Please provide valid uploading type."),
    INVALID_ATTACHMENT_ID("E5043", "File with id {0} does not exist or deleted."),
    INVALID_DOWNLOAD_REQUEST("E5044", "Please provide valid parameters for file download."),
    INVALID_ORDER_BY_COUNT("E5045", "Three level of order is allowed."),
    NO_DATA_FOR_FILTER("E5046", "No data found for selected filter."),
    ALL_RECORD_FAILED("E5047", "All records processing failed."),
    ERROR_WHILE_UPLOADING("E5048", "Error occurred while uploading file."),

    AGREEMENT_ALREADY_SIGNED("E5049", "Agreement is already signed by user {0}."),

    USER_WITH_LOGIN_NAME_EXIST("E5050", "User with login-name {0} already exist."),

    CUSTOMER_WITH_DOMAIN_EXIST("E5051", "Customer with domain {0} already exist."),
    ;

    private final String code;
    private final String description;

    ApplicationCode(String number, String description) {
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
