/**
 * 
 */
package it.polimi.ingsw2.swim.exceptions;

/**
 * @author Administrator
 *
 */
public class UserAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4564275930001936972L;

	/**
	 * 
	 */
	public UserAlreadyExistsException() {
		super();
	}

	/**
	 * @param message
	 */
	public UserAlreadyExistsException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public UserAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UserAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

}
