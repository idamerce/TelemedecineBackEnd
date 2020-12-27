package com.telemedecine.telemedecine.exception;

import lombok.Data;

@Data
public class CustomError {

    private String errorMessage;
    public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
