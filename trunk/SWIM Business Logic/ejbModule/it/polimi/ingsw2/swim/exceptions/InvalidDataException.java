/**
 * 
 */
package it.polimi.ingsw2.swim.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.InvalidValue;

/**
 * @author Administrator
 *
 */
public class InvalidDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2566268939341348143L;
	
	public final String[] invalidFields;
	
	public InvalidDataException(InvalidValue[] validationMessages) {
		List<String> tempInvalid = new ArrayList<String>(); 
		for(InvalidValue i : validationMessages){
			String p = i.getPropertyName();
			if(p != null && !p.isEmpty()){
				tempInvalid.add(i.getPropertyName());	
			}
		}
		invalidFields = (String[]) tempInvalid.toArray();
	}

	public InvalidDataException() {
		invalidFields = null;
	}
}