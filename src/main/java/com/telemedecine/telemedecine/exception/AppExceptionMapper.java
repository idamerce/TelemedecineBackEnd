package com.telemedecine.telemedecine.exception;

public class AppExceptionMapper {
    private AppExceptionMapper() {
    }

    public static ExceptionDto toExceptionDto(AppException exception) {
        return new ExceptionDto()
                .setCode(exception.getExceptionCode().name())
                .setMessage(exception.getMsg())
                .setTicketId(exception.getTickedId())
                .setTime(exception.getTime())
                .setStatus(exception.getExceptionCode().getStatus())
                .setErrors(exception.getErrors());
    }
}
