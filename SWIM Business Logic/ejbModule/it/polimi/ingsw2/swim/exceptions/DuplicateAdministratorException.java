package it.polimi.ingsw2.swim.exceptions;

public class DuplicateAdministratorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 136991185747815765L;

	public DuplicateAdministratorException() {
		super();
	}

	public DuplicateAdministratorException(String message) {
		super(message);
	}

	public DuplicateAdministratorException(Throwable cause) {
		super(cause);
	}

	public DuplicateAdministratorException(String message, Throwable cause) {
		super(message, cause);
	}

}
