package org.young.commons.exception;

public class ExpiredException extends DatahubException {

	/**
	 * 过期异常
	 */
	private static final long serialVersionUID = 1L;

	public ExpiredException() {
		super();
	}

	public ExpiredException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExpiredException(String message) {
		super(message);
	}

	public ExpiredException(Throwable cause) {
		super(cause);
	}

}
