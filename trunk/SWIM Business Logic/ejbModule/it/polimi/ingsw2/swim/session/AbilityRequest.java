package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.UserDoesNotExixtException;
import it.polimi.ingsw2.swim.util.DAO;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

/**
 * Session Bean implementation class AbilityRequest
 */
@Stateless
public class AbilityRequest implements AbilityRequestRemote {

	private static EntityManager em = DAO.getInstance().getEntityManager();

	/**
	 * Default constructor.
	 */
	public AbilityRequest() {
		super();
	}

	@Override
	public void registerSubscription(String id, String abilityName) throws UserDoesNotExixtException, InvalidDataException {
		User user = em.find(User.class, Long.parseLong(id));
		if (user == null) {
			throw new UserDoesNotExixtException();
		}

		Ability ability = em.find(Ability.class, abilityName);
		if (ability == null) {
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
		User user = em.find(User.class, Long.parseLong(id));
		if (user == null) {
			throw new UserDoesNotExixtException();
		}

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
