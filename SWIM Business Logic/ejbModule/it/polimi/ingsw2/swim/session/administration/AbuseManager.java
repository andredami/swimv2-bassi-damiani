package it.polimi.ingsw2.swim.session.administration;

import it.polimi.ingsw2.swim.entities.Abuse;
import it.polimi.ingsw2.swim.exceptions.AlreadyHandledException;
import it.polimi.ingsw2.swim.session.remote.AbuseManagerRemote;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class AbuseManager
 */
@Stateless
@Remote
public class AbuseManager implements AbuseManagerRemote {

	@PersistenceContext(unitName = "persistentData")
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public AbuseManager() {
        super();
    }
    
    @SuppressWarnings("unchecked")
    @Override
	public List<Abuse> getAbuseList(){
    	return em.createQuery("SELECT a FROM Abuse ORDER BY a.handled ASC").getResultList();
    }
    
    @Override
    public void markAbuseAsHandled(String abuseId) throws AlreadyHandledException{
    	Abuse abuse = em.find(Abuse.class, abuseId);
    	if(abuse != null){
    		abuse.handle();
    		em.merge(abuse);
    	}
    }
    
    @Override
    public void removeAbuse(String abuseId){
    	Abuse abuse = em.find(Abuse.class, abuseId);
    	if(abuse != null){
    		em.remove(abuse);
    	}
    }

}
