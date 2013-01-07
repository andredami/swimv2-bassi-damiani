/**
 * 
 */
package it.polimi.ingsw2.swim.exceptions;

/**
 * @author Administrator
 *
 */
public class InvalidGenderException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8358887417429102845L;

	/**
	 * 
	 */
	public InvalidGenderException() {
		super();
	}

	/**
	 * @param message
	 */
	public InvalidGenderException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InvalidGenderException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidGenderException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvalidGenderException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
