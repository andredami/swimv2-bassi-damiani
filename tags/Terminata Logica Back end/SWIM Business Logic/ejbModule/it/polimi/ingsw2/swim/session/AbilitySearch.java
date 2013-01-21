package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.session.remote.AbilitySearchRemote;
import it.polimi.ingsw2.swim.util.DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Session Bean implementation class AbilitySearch
 */
@Stateless
public class AbilitySearch implements AbilitySearchRemote {

	private static EntityManager em = DAO.getInstance().getEntityManager();

	/**
	 * Default constructor.
	 */
	public AbilitySearch() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ability> findAbility(String name) {
		Query query = em.createQuery("SELECT a" +
				"FROM Ability a, IN (a.alias) AS al" +
				"WHERE a.name LIKE ':namePattern'" +
				"OR al.name LIKE ':namePattern'");
		
		query.setParameter("namePattern", "%" + name + "%");
		return query.getResultList();
	}

}
