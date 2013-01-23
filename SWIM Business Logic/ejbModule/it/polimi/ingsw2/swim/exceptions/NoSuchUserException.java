/**
 * 
 */
package it.polimi.ingsw2.swim.exceptions;

/**
 * @author Administrator
 *
 */
public class NoSuchUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 968244269557905985L;

	/**
	 * 
	 */
	public NoSuchUserException() {
		super();
	}

	/**
	 * @param message
	 */
	public NoSuchUserException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public NoSuchUserException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NoSuchUserException(String message, Throwable cause) {
		super(message, cause);
	}

}
