/**
 * 
 */
package it.polimi.ingsw2.swim.exceptions;

/**
 * @author Administrator
 *
 */
public class InvalidAgeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7508718731012514912L;

	/**
	 * 
	 */
	public InvalidAgeException() {
		super();
	}

	/**
	 * @param message
	 */
	public InvalidAgeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InvalidAgeException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidAgeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvalidAgeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
