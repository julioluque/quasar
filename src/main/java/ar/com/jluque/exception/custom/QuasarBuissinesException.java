package ar.com.jluque.exception.custom;

public class QuasarBuissinesException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String DESCRIPTION = "Quasar General Error";

	public QuasarBuissinesException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}
}
