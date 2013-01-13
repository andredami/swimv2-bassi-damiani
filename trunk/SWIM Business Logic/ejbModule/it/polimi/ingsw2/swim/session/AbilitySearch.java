package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.entities.Alias;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class AbilitySearch
 */
@Stateless
public class AbilitySearch implements AbilitySearchRemote {

	@PersistenceContext(unitName = "persistentData")
	private static EntityManager em;

	/**
	 * Default constructor.
	 */
	public AbilitySearch() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ability> findAbility(String name) {
		List<Ability> abilities = new LinkedList<Ability>();

		Query exact = em.createQuery("SELECT a" + "FROM Ability a"
				+ "WHERE a.name LIKE :namePattern");
		exact.setParameter("namePattern", "%" + name + "%");
		abilities.addAll(exact.getResultList());

		Query aliasList = em.createQuery("SELECT a" + "FROM Alias a"
				+ "WHERE a.ability LIKE :namePattern");
		aliasList.setParameter("namePattern", "%" + name + "%");

		for (Object res : aliasList.getResultList()) {
			abilities.add(((Alias) res).getAbility());
		}

		return abilities;
	}

}
