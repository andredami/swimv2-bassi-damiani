/**
 * 
 */
package it.polimi.ingsw2.swim.validation;

import java.util.Calendar;

import org.hibernate.mapping.Property;
import org.hibernate.validator.PropertyConstraint;
import org.hibernate.validator.Validator;

/**
 * @author Administrator
 * 
 */
public class AgeValidator implements Validator<OfAge>, PropertyConstraint {

	private Calendar now;
	private int minAge;

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.hibernate.validator.PropertyConstraint#apply(org.hibernate.mapping
	 * .Property)
	 */
	@Override
	public void apply(Property arg0) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.hibernate.validator.Validator#initialize(java.lang.annotation.Annotation
	 * )
	 */
	@Override
	public void initialize(OfAge arg0) {
		now = Calendar.getInstance();
		minAge = arg0.minimumAge();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.validator.Validator#isValid(java.lang.Object)
	 */
	@Override
	public boolean isValid(Object arg0) {
		Calendar arg;
		if (arg0 instanceof Calendar) {
			arg = (Calendar) arg0;
		} else {
			return false;
		}

		int yearOffset = now.get(Calendar.YEAR) - arg.get(Calendar.YEAR);

		if (yearOffset > minAge) {
			return true;
		} else if (yearOffset == minAge) {
			int monthOffset = now.get(Calendar.MONTH) - arg.get(Calendar.MONTH);
			if (monthOffset > 0) {
				return true;
			} else if (monthOffset == 0) {
				int dayOffset = now.get(Calendar.DAY_OF_MONTH)
						- arg.get(Calendar.DAY_OF_MONTH);
				if (dayOffset <= 0) {
					return true;
				}
			}
		}

		return false;
	}
}
