package com.kygo.service.exception;

public class FTPClientException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FTPClientException() {
		super();
	}

	public FTPClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FTPClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public FTPClientException(String message) {
		super(message);
	}

	public FTPClientException(Throwable cause) {
		super(cause);
	}

	
	
}
