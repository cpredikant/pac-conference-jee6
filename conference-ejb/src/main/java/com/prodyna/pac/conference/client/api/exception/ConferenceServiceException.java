package com.prodyna.pac.conference.client.api.exception;

public class ConferenceServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ConferenceServiceException() {
		super();
	}
	
	public ConferenceServiceException(String message) {
		super(message);
	}
	
	public ConferenceServiceException(Throwable cause) {
		super(cause);
	}
	
	public ConferenceServiceException(String message, Throwable cause) {
		super(message,cause);
	}
	
}
