/**
 * 
 */
package it.polimi.ingsw2.swim.exceptions;

/**
 * @author Administrator
 *
 */
public class AlreadyActiveException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5713715435205577667L;

	/**
	 * 
	 */
	public AlreadyActiveException() {
		super();
	}

	/**
	 * @param message
	 */
	public AlreadyActiveException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AlreadyActiveException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AlreadyActiveException(String message, Throwable cause) {
		super(message, cause);
	}
}
