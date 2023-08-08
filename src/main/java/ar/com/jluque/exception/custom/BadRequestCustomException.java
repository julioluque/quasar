package ar.com.jluque.exception.custom;

public class BadRequestCustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String DESCRIPTION = "Bad Request Exception";

	public BadRequestCustomException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}
}
