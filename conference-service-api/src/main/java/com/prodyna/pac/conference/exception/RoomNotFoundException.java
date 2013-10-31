package com.prodyna.pac.conference.exception;

public class RoomNotFoundException extends ServiceException {

	private static final long serialVersionUID = 1L;
	
	public RoomNotFoundException() {
		super();
	}
	
	public RoomNotFoundException(String message) {
		super(message);
	}
	
	public RoomNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public RoomNotFoundException(String message, Throwable cause) {
		super(message,cause);
	}
	
}
