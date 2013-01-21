/**
 * 
 */
package it.polimi.ingsw2.swim.exceptions;

/**
 * @author Administrator
 *
 */
public class DuplicateAbilityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7299273837666204284L;

	/**
	 * 
	 */
	public DuplicateAbilityException() {
		super();
	}

	/**
	 * @param arg0
	 */
	public DuplicateAbilityException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public DuplicateAbilityException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public DuplicateAbilityException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public DuplicateAbilityException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
