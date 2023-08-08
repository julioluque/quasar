package ar.com.jluque.exception.custom;

public class ConflictCustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String DESCRIPTION = "Conflict Exception";

	public ConflictCustomException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}
}
