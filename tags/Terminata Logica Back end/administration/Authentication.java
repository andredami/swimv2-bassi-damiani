package it.polimi.ingsw2.swim.session.administration;

import it.polimi.ingsw2.swim.entities.Administrator;
import it.polimi.ingsw2.swim.session.remote.AdministrationAuthenticationRemote;
import it.polimi.ingsw2.swim.util.DAO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 * Session Bean implementation class Authentication
 */
@Stateless(name = "AdministrationAuthentication")
public class Authentication implements AdministrationAuthenticationRemote {

	private static final EntityManager em = DAO.getInstance()
			.getEntityManager();
	
    /**
     * Default constructor. 
     */
    public Authentication() {
        super();
    }

    @Override
    public boolean authenticate(String username, String password) {
		Administrator user;
		try {
			user = (Administrator) em.createNamedQuery("getAdministratorByUsername")
					.setParameter("username", username).getSingleResult();
		} catch (NoResultException e) {
			return false;
		} catch (NonUniqueResultException e) {
			throw new RuntimeException();
		}

		if (!user.checkPassword(password)) {
			return false;
		}

		return true;
	}
}
