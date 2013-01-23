/**
 * 
 */
package it.polimi.ingsw2.swim.exceptions;

/**
 * @author Administrator
 *
 */
public class InvalidActivationCode extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 857367601830868713L;

	/**
	 * 
	 */
	public InvalidActivationCode() {
		super();
	}

	/**
	 * @param message
	 */
	public InvalidActivationCode(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InvalidActivationCode(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidActivationCode(String message, Throwable cause) {
		super(message, cause);
	}

}
