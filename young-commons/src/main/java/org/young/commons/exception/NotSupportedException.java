package org.young.commons.exception;

public class NotSupportedException extends DatahubException {

	/**
	 * 不支持的异常
	 */
	private static final long serialVersionUID = 1L;

	public NotSupportedException() {
		super();
	}

	public NotSupportedException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotSupportedException(String message) {
		super(message);
	}

	public NotSupportedException(Throwable cause) {
		super(cause);
	}

}
