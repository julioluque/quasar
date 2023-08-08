package ar.com.jluque.exception.custom;

public class QuasarBussinesNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String DESCRIPTION = "General Bussines Not Found Exception.";

	public QuasarBussinesNotFoundException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}
}
