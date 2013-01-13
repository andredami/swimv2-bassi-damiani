/**
 * 
 */
package it.polimi.ingsw2.swim.exceptions;

/**
 * @author Administrator
 *
 */
public class UserDoesNotExixtException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 968244269557905985L;

	/**
	 * 
	 */
	public UserDoesNotExixtException() {
		super();
	}

	/**
	 * @param message
	 */
	public UserDoesNotExixtException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public UserDoesNotExixtException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UserDoesNotExixtException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public UserDoesNotExixtException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
