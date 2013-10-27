package com.prodyna.pac.conference.client.api.exception;

public class SpeakerNotAvailableServiceException extends ConferenceServiceException {

	private static final long serialVersionUID = 1L;
	
	public SpeakerNotAvailableServiceException() {
		super();
	}
	
	public SpeakerNotAvailableServiceException(String message) {
		super(message);
	}
	
	public SpeakerNotAvailableServiceException(Throwable cause) {
		super(cause);
	}
	
	public SpeakerNotAvailableServiceException(String message, Throwable cause) {
		super(message,cause);
	}
	
}
