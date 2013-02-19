/**
 * 
 */
package it.polimi.ingsw2.swim.validation;

import java.net.MalformedURLException;
import java.net.URL;

import org.hibernate.mapping.Property;
import org.hibernate.validator.PropertyConstraint;
import org.hibernate.validator.Validator;

/**
 * @author Administrator
 *
 */
public class URLValidator implements PropertyConstraint, Validator<URLType> {

	/* (non-Javadoc)
	 * @see org.hibernate.validator.Validator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(URLType arg0) {
	}

	/* (non-Javadoc)
	 * @see org.hibernate.validator.Validator#isValid(java.lang.Object)
	 */
	@Override
	public boolean isValid(Object arg0) {
		String arg;
		if(arg0 == null){
			return true;
		}
		if(arg0 instanceof String){
			arg = (String) arg0;
		} else {
			return false;
		}
		
		try {
			new URL(arg);
		} catch (MalformedURLException e) {
			return false;
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.validator.PropertyConstraint#apply(org.hibernate.mapping.Property)
	 */
	@Override
	public void apply(Property arg0) {
	}

}
