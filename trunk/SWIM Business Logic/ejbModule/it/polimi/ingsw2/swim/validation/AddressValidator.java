/**
 * 
 */
package it.polimi.ingsw2.swim.validation;

import it.polimi.ingsw2.swim.entities.User.Address;

import org.hibernate.mapping.Property;
import org.hibernate.validator.PropertyConstraint;
import org.hibernate.validator.Validator;


/**
 * @author Administrator
 * 
 */
public class AddressValidator implements Validator<AddressType>,
		PropertyConstraint {

	@Override
	public boolean isValid(Object value) {
		if(value == null){
			return true;
		}
		
		Address address;
		if(value instanceof Address){
			address = (Address) value;
		} else {
			return false;
		}
		
		
		if(address.getCity() == null || (address.getZip() == null && address.getProvince() == null)){
			return false;
		}
		
		//TODO: Connect to external web service to georeference the address in order to ensure validity
		
		return true;
	}

	@Override
	public void apply(Property arg0) {
	}

	@Override
	public void initialize(AddressType arg0) {
	}
}
