package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.session.remote.AbilitySearchRemote;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class AbilitySearch
 */
@Stateless
@Remote
public class AbilitySearch implements AbilitySearchRemote {

	@PersistenceContext(unitName = "persistentData")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public AbilitySearch() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ability> findAbility(String name) {
		Query query;
		if(name == null || name.isEmpty()){
			query = em.createQuery("SELECT b FROM Ability b");
		} else {
		query = em.createQuery("SELECT DISTINCT b FROM Ability b LEFT JOIN FETCH b.alias c WHERE (b.name LIKE :namePattern) OR (c.name LIKE :namePattern)");
		query.setParameter("namePattern", "%" + name + "%");
		}
		return query.getResultList();
	}

}
