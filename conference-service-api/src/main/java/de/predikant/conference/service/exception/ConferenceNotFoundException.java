package de.predikant.conference.service.exception;

public class ConferenceNotFoundException extends ServiceException {

	private static final long serialVersionUID = 1L;
	
	public ConferenceNotFoundException() {
		super();
	}
	
	public ConferenceNotFoundException(String message) {
		super(message);
	}
	
	public ConferenceNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public ConferenceNotFoundException(String message, Throwable cause) {
		super(message,cause);
	}
	
}
