package com.prodyna.pac.conference.exception;

public class SpeakerNotFoundException extends ConferenceServiceException {

	private static final long serialVersionUID = 1L;
	
	public SpeakerNotFoundException() {
		super();
	}
	
	public SpeakerNotFoundException(String message) {
		super(message);
	}
	
	public SpeakerNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public SpeakerNotFoundException(String message, Throwable cause) {
		super(message,cause);
	}
	
}
