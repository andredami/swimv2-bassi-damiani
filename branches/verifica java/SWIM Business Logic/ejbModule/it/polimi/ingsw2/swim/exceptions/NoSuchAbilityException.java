/**
 * 
 */
package it.polimi.ingsw2.swim.exceptions;

/**
 * @author Administrator
 *
 */
public class NoSuchAbilityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6071866662992050986L;

	/**
	 * 
	 */
	public NoSuchAbilityException() {
		super();
	}

	/**
	 * @param message
	 */
	public NoSuchAbilityException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public NoSuchAbilityException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NoSuchAbilityException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public NoSuchAbilityException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
