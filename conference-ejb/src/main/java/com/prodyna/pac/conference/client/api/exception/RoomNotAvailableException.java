package com.prodyna.pac.conference.client.api.exception;

public class RoomNotAvailableException extends ConferenceServiceException {

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
