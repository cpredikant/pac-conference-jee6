package de.predikant.conference.service.exception;

public class SpeakerHasTalkNotFoundException extends ServiceException {

	private static final long serialVersionUID = 1L;
	
	public SpeakerHasTalkNotFoundException() {
		super();
	}
	
	public SpeakerHasTalkNotFoundException(String message) {
		super(message);
	}
	
	public SpeakerHasTalkNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public SpeakerHasTalkNotFoundException(String message, Throwable cause) {
		super(message,cause);
	}
	
}
