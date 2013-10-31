package com.prodyna.pac.conference.exception;

public class TalkNotFoundException extends ServiceException {

	private static final long serialVersionUID = 1L;
	
	public TalkNotFoundException() {
		super();
	}
	
	public TalkNotFoundException(String message) {
		super(message);
	}
	
	public TalkNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public TalkNotFoundException(String message, Throwable cause) {
		super(message,cause);
	}
	
}
