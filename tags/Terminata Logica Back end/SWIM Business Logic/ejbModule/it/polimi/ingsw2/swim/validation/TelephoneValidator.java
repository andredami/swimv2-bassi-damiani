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
public class TelephoneValidator implements PropertyConstraint,
		Validator<Telephone> {

	/* (non-Javadoc)
	 * @see org.hibernate.validator.Validator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(Telephone arg0) {
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
		
		arg.replaceAll("[ .-()#]", "");
		if(arg.matches("^((([+]{1})(|[0]{2}))[0-9]{2}){0,1}[0-9]{3,}$")){
			return true;
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.validator.PropertyConstraint#apply(org.hibernate.mapping.Property)
	 */
	@Override
	public void apply(Property arg0) {
	}

}
