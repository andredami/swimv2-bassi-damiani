/**
 * 
 */
package it.polimi.ingsw2.swim.validation;

import org.hibernate.mapping.Property;
import org.hibernate.validator.PropertyConstraint;
import org.hibernate.validator.Validator;

/**
 * @author Administrator
 *
 */
public class NameValidator implements Validator<Name>, PropertyConstraint {


	/* (non-Javadoc)
	 * @see org.hibernate.validator.PropertyConstraint#apply(org.hibernate.mapping.Property)
	 */
	@Override
	public void apply(Property arg0) {
	}

	/* (non-Javadoc)
	 * @see org.hibernate.validator.Validator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(Name arg0) {
	}

	/* (non-Javadoc)
	 * @see org.hibernate.validator.Validator#isValid(java.lang.Object)
	 */
	@Override
	public boolean isValid(Object arg0) {
		String arg;
		if(arg0 instanceof String){
			arg = (String) arg0;
		} else {
			return false;
		}
		
		if(arg.matches("[^[ a-zA-Z]]")){
			return false;
		}
		return true;
	}

}
