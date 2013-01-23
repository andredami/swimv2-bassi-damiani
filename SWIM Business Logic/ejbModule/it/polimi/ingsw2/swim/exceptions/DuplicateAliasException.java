/**
 * 
 */
package it.polimi.ingsw2.swim.exceptions;

/**
 * @author Administrator
 *
 */
public class DuplicateAliasException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8051939458908231341L;

	/**
	 * 
	 */
	public DuplicateAliasException() {
		super();
	}

	/**
	 * @param message
	 */
	public DuplicateAliasException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DuplicateAliasException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DuplicateAliasException(String message, Throwable cause) {
		super(message, cause);
	}

}
