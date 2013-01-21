/**
 * 
 */
package it.polimi.ingsw2.swim.exceptions;

/**
 * @author Administrator
 *
 */
public class LocationMissingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1755364716730459854L;

	/**
	 * 
	 */
	public LocationMissingException() {
		super();
	}

	/**
	 * @param arg0
	 */
	public LocationMissingException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public LocationMissingException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public LocationMissingException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public LocationMissingException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
