package com.sijan.customermanagement.exception;

public class ErrorResponse {
    private int status;    // HTTP status code
    private String message; // Error message
	public ErrorResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}
    
    
}