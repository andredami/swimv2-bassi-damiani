package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.UserDoesNotExixtException;
import it.polimi.ingsw2.swim.session.remote.AbilityRequestRemote;
import it.polimi.ingsw2.swim.util.DAO;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 * Session Bean implementation class AbilityRequest
 */
@Stateless
public class AbilityRequest implements AbilityRequestRemote {

	private static final EntityManager em = DAO.getInstance().getEntityManager();
	

	/**
	 * Default constructor.
	 */
	public AbilityRequest() {
		super();
	}

	@Override
	public void registerSubscription(String id, String abilityName) throws UserDoesNotExixtException, InvalidDataException {
		User user = (new ProfileManager()).getUserWithAbilities(id);
		
		Ability ability; 
		try {
			ability = (Ability) em.createNamedQuery("getAbilityWithSubscriber").setParameter("ability", abilityName.toLowerCase()).getSingleResult();
		} catch (NoResultException e){
			throw new InvalidDataException();
		} catch(NonUniqueResultException  e){
			e.printStackTrace();
			throw new InvalidDataException();
		}

		if (ability.isStub()) {
			ability.addSubscriber(user);
			em.merge(ability);
		} else {
			user.addAbility(ability);
			em.merge(user);
		}
	}
	
	@Override
	public void registerNewRequest(String id, String abilityName,
			String description) throws UserDoesNotExixtException {
		User user = (new ProfileManager()).getUserWithAbilities(id);
		
		Ability stub = new Ability(abilityName, description, user);

		try {
			em.persist(stub);
		} catch (EntityExistsException e) {
			stub = em.find(Ability.class, abilityName);
			if (stub != null) {
				user.addAbility(stub);
				em.merge(user);
			}
		}
	}
}
