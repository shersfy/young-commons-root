package org.young.commons.exception;

public class TooManyConnectionException extends DatahubException {

	/**
	 * 连接过多异常
	 */
	private static final long serialVersionUID = 1L;

	public TooManyConnectionException() {
		super();
	}

	public TooManyConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

	public TooManyConnectionException(String message) {
		super(message);
	}

	public TooManyConnectionException(Throwable cause) {
		super(cause);
	}

}
