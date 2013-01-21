/**
 * 
 */
package it.polimi.ingsw2.swim.exceptions;

/**
 * @author Administrator
 *
 */
public class NoSuchRequestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7606726860056223950L;

	/**
	 * 
	 */
	public NoSuchRequestException() {
		super();
	}

	/**
	 * @param arg0
	 */
	public NoSuchRequestException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public NoSuchRequestException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public NoSuchRequestException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public NoSuchRequestException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
