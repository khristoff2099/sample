package com.martscompany.restapi.exceptions;

public class ExceptionMessages {
	
	String message;
	String details;

    public ExceptionMessages(String message, String details) {
        this.message = message;
        this.details = details;
    }
    
    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
