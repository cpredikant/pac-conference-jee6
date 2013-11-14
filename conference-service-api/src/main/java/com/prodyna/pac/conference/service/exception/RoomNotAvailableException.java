package com.prodyna.pac.conference.service.exception;

public class RoomNotAvailableException extends ServiceException {

	private static final long serialVersionUID = 1L;
	
	public RoomNotAvailableException() {
		super();
	}
	
	public RoomNotAvailableException(String message) {
		super(message);
	}
	
	public RoomNotAvailableException(Throwable cause) {
		super(cause);
	}
	
	public RoomNotAvailableException(String message, Throwable cause) {
		super(message,cause);
	}
	
}
