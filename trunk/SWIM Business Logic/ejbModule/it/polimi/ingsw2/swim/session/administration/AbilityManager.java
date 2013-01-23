package it.polimi.ingsw2.swim.session.administration;

import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.exceptions.DuplicateAbilityException;
import it.polimi.ingsw2.swim.exceptions.NoSuchAbilityException;
import it.polimi.ingsw2.swim.session.remote.AbilityManagerRemote;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class AbilityManager
 */
@Stateless
@Remote
public class AbilityManager implements AbilityManagerRemote {

	@PersistenceContext(unitName = "persistentData")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public AbilityManager() {
		super();
	}

	@Override
	public void editAbilityName(String abilityId, String name)
			throws DuplicateAbilityException, NoSuchAbilityException {
		Ability ability = em.find(Ability.class, abilityId);
		if (ability == null) {
			throw new NoSuchAbilityException();
		}
		Ability prev = em.find(Ability.class, name.toLowerCase());
		if (prev != null) {
			throw new DuplicateAbilityException();
		}
		ability.setName(name);
		em.merge(ability);
	}

	@Override
	public void editAbilityDescription(String abilityId, String description)
			throws DuplicateAbilityException, NoSuchAbilityException {
		if (description == null) {
			throw new IllegalArgumentException();
		}

		Ability ability = em.find(Ability.class, abilityId);
		if (ability == null) {
			throw new NoSuchAbilityException();
		}
		ability.setDescription(description);
		em.merge(ability);
	}

	@Override
	public void removeAbilityAlias(String abilityId, Set<String> aliasId) throws NoSuchAbilityException{
		Ability ability;
		try{
			ability = (Ability) em.createNamedQuery("getAbilityWithAlias").setParameter("ability", abilityId).getSingleResult();
    	} catch(NoResultException e) {
    		throw new NoSuchAbilityException();
    	}
    	
		for(String alias: aliasId){
    			ability.removeAlias(alias);
    	}
    	em.merge(ability);
    }
	
	@Override
	public void addAbilityAlias(String abilityId, Set<String> aliasId) throws NoSuchAbilityException{
		Ability ability;
		try{
			ability = (Ability) em.createNamedQuery("getAbilityWithAlias").setParameter("ability", abilityId).getSingleResult();
    	} catch(NoResultException e) {
    		throw new NoSuchAbilityException();
    	}
    	
		for(String alias: aliasId){
    			ability.addAlias(alias);
    	}
    	em.merge(ability);
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ability> retriveAbilityList(){
		return em.createQuery("SELECT b FROM Ability b WHERE b.isStub = FALSE").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ability> retriveAbilityList(String searchKey){
		searchKey = "%" + searchKey + "%";
		return em.createQuery("SELECT b FROM Ability b WHERE b.name LIKE ':key' AND b.isStub = FALSE").setParameter("key", searchKey).getResultList();
	}
	
	@Override
	public void deleteAbility(String abilityId) throws NoSuchAbilityException{
		Ability ability = em.find(Ability.class, abilityId);
		if (ability == null) {
			throw new NoSuchAbilityException();
		}
		em.remove(ability);
	}
	
}
