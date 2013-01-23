/**
 * 
 */
package it.polimi.ingsw2.swim.exceptions;

/**
 * @author Administrator
 *
 */
public class AlreadyHandledException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6312576705146911643L;

	/**
	 * 
	 */
	public AlreadyHandledException() {
		super();
	}

	/**
	 * @param message
	 */
	public AlreadyHandledException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AlreadyHandledException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AlreadyHandledException(String message, Throwable cause) {
		super(message, cause);
	}

}
