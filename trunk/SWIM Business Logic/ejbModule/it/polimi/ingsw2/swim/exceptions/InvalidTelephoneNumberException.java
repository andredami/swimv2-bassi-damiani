/**
 * 
 */
package it.polimi.ingsw2.swim.exceptions;

/**
 * @author Administrator
 *
 */
public class InvalidTelephoneNumberException extends InvalidInputException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5663395380842664544L;

	/**
	 * 
	 */
	public InvalidTelephoneNumberException() {
	}

	/**
	 * @param message
	 */
	public InvalidTelephoneNumberException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InvalidTelephoneNumberException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidTelephoneNumberException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvalidTelephoneNumberException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
