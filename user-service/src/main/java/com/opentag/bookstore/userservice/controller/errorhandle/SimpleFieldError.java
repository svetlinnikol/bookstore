package com.opentag.bookstore.userservice.controller.errorhandle;

/**
 * Object returned in case of an error in the controllers
 */
public final class SimpleFieldError {
	
	private final String fieldName;
    private final String message;

    public SimpleFieldError(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getFieldName() {
        return fieldName;
    }
}
