package ar.com.jluque.exception.custom;

public class DataIntegrityCustomException extends ConflictCustomException {
	
	private static final long serialVersionUID = 1L;

	private static final String DESCRIPTION = "Data Integrity Violation Exception";

	public DataIntegrityCustomException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}

}
