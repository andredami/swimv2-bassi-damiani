/**
 * 
 */
package it.polimi.ingsw2.swim.exceptions;

/**
 * @author Administrator
 *
 */
public class InvalidAddressException extends InvalidInputException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5028944714688555384L;

	/**
	 * 
	 */
	public InvalidAddressException() {
		super();
	}

	/**
	 * @param message
	 */
	public InvalidAddressException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InvalidAddressException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidAddressException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvalidAddressException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
