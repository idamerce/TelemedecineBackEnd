package com.telemedecine.telemedecine.exception;

public enum ExceptionCode {
    API_VALIDATION(400, "Invalid request payload"),
    API_MISSING_PARAMETER(400, "Mandatory request parameter is missing"),
    API_BAD_REQUEST(400, "Invalid request syntax"),
    PAGE_NOT_FOUND(400, "Requested page not found"),
    API_UNAUTHORIZED(401, "Request not authenticated"),
    API_FORBIDDEN(403, "Client does not have sufficient permission"),
    API_RESOURCE_NOT_FOUND(404, "Resource is not found"),
    API_ERROR_INTERNAL(500, "Internal server error.");

    private final int status;

    private final String code;

    ExceptionCode(int status, String code) {
        this.status = status;
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}

