/**
 * 
 */
package it.polimi.ingsw2.swim.exceptions;

/**
 * @author Administrator
 *
 */
public class InvalidFullnameException extends InvalidInputException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2381312765149223936L;

	/**
	 * 
	 */
	public InvalidFullnameException() {
		super();
	}

	/**
	 * @param message
	 */
	public InvalidFullnameException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InvalidFullnameException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidFullnameException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvalidFullnameException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
