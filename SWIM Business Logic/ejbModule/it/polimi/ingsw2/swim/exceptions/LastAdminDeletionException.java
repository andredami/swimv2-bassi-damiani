/**
 * 
 */
package it.polimi.ingsw2.swim.exceptions;

/**
 * @author Administrator
 *
 */
public class LastAdminDeletionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3795364854695080998L;

	/**
	 * 
	 */
	public LastAdminDeletionException() {
		super();
	}

	/**
	 * @param message
	 */
	public LastAdminDeletionException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public LastAdminDeletionException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public LastAdminDeletionException(String message, Throwable cause) {
		super(message, cause);
	}

}
