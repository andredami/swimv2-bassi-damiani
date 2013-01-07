/**
 * 
 */
package it.polimi.ingsw2.swim.exceptions;

/**
 * @author Administrator
 *
 */
public class InvalidEmailAddressException extends InvalidInputException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3568516573761208564L;

	/**
	 * 
	 */
	public InvalidEmailAddressException() {
		super();
	}

	/**
	 * @param message
	 */
	public InvalidEmailAddressException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InvalidEmailAddressException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidEmailAddressException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvalidEmailAddressException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}