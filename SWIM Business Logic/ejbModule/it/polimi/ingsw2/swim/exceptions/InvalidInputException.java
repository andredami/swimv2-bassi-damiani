/**
 * 
 */
package it.polimi.ingsw2.swim.exceptions;

/**
 * @author Administrator
 *
 */
public class InvalidInputException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2536634811927844251L;

	/**
	 * 
	 */
	public InvalidInputException() {
		super();
	}

	/**
	 * @param message
	 */
	public InvalidInputException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InvalidInputException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidInputException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvalidInputException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
