package com.telemedecine.telemedecine.exception;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AppException extends RuntimeException {
    private ExceptionCode exceptionCode;
    private String tickedId;
    private String msg;
    private String technicalMessage;
    private LocalDateTime time;
    private Set<Error> errors = new HashSet<>();

    public AppException(ExceptionCode exceptionCode) {
        this(exceptionCode, null, null, null);
    }

    public AppException(ExceptionCode exceptionCode, String message) {
        this(exceptionCode, message, null, null);
    }

    public AppException(ExceptionCode exceptionCode, String message, String technicalMessage) {
        this(exceptionCode, message, technicalMessage, null);
    }

    public AppException(ExceptionCode exceptionCode, String message, String technicalMessage, Throwable cause) {
        super(message, cause);
        this.msg = message;
        this.technicalMessage = technicalMessage;
        this.exceptionCode = exceptionCode;
        tickedId = UUID.randomUUID().toString();
        this.time = LocalDateTime.now();
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }

    public AppException setExceptionCode(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
        return this;
    }

    public String getTickedId() {
        return tickedId;
    }

    public AppException setTickedId(String tickedId) {
        this.tickedId = tickedId;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public AppException setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getTechnicalMessage() {
        return technicalMessage;
    }

    public AppException setTechnicalMessage(String technicalMessage) {
        this.technicalMessage = technicalMessage;
        return this;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public AppException setTime(LocalDateTime time) {
        this.time = time;
        return this;
    }

    public Set<Error> getErrors() {
        return errors;
    }

    public AppException setErrors(Set<Error> errors) {
        this.errors = errors;
        return this;
    }
}
