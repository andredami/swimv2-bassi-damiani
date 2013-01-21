/**
 * 
 */
package it.polimi.ingsw2.swim.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.validator.ValidatorClass;

/**
 * @author Administrator
 *
 */
@ValidatorClass(AddressValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE}) 
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AddressType {
	String message() default "Not valid as an address";
}
