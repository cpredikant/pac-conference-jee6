package com.prodyna.pac.conference.client.api.exception;

public class SpeakerNotAvailableException extends ConferenceServiceException {

	private static final long serialVersionUID = 1L;
	
	public SpeakerNotAvailableException() {
		super();
	}
	
	public SpeakerNotAvailableException(String message) {
		super(message);
	}
	
	public SpeakerNotAvailableException(Throwable cause) {
		super(cause);
	}
	
	public SpeakerNotAvailableException(String message, Throwable cause) {
		super(message,cause);
	}
	
}
