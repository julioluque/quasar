package ar.com.jluque.exception.custom;

public class ForbiddenCustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String DESCRIPTION = "Forbidden Exception";

	public ForbiddenCustomException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}

}
