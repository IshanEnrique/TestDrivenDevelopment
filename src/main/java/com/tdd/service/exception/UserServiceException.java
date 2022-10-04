package com.tdd.service.exception;

public class UserServiceException extends RuntimeException {

	private static final long serialVersionUID = 3970164023317067396L;

	public UserServiceException(String message) {
		super(message);
	}

}
