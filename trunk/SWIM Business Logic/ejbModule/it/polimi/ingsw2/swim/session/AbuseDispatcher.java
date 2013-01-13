package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.entities.Abuse;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.InvalidValue;

/**
 * Session Bean implementation class AbuseDispatcher
 */
@Stateless
public class AbuseDispatcher implements AbuseDispatcherRemote {

	private static ClassValidator<Abuse> abuseValidator = new ClassValidator<Abuse>(
			Abuse.class);
	
	@PersistenceContext(unitName = "persistentData")
	private static EntityManager em;
	
    /**
     * Default constructor. 
     */
    public AbuseDispatcher() {
    	super();
    }
    
    @Override
    public void registerAbuse(String email, String description) throws InvalidDataException{
    	Abuse report = new Abuse(email, description);
		em.persist(sanitize(report));
    }
    
    private Abuse sanitize(Abuse report) throws InvalidDataException{
    	InvalidValue[] validationMessages = abuseValidator.getInvalidValues(report);

		if (validationMessages.length != 0) {
			throw new InvalidDataException(validationMessages);
		}
		
		return report;
    }
}
