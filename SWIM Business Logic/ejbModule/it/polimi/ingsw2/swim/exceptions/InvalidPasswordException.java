/**
 * 
 */
package it.polimi.ingsw2.swim.exceptions;

/**
 * @author Administrator
 *
 */
public class InvalidPasswordException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8620261635301175452L;

	/**
	 * 
	 */
	public InvalidPasswordException() {
		super();
	}

	/**
	 * @param message
	 */
	public InvalidPasswordException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InvalidPasswordException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

}
