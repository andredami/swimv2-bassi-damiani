package it.polimi.ingsw2.swim.session.administration;

import it.polimi.ingsw2.swim.entities.Administrator;
import it.polimi.ingsw2.swim.session.remote.AdministrationAuthenticationRemote;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class Authentication
 */
@Stateless(name = "AdministrationAuthentication")
@Remote
public class Authentication implements AdministrationAuthenticationRemote {

	@PersistenceContext(unitName = "persistentData")
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public Authentication() {
        super();
    }

    @Override
    public Administrator authenticate(String username, String password) throws NoSuchUserException {
		Administrator user;
		try {
			user = (Administrator) em.createNamedQuery("getAdministratorByUsername")
					.setParameter("username", username).getSingleResult();
		} catch (NoResultException e) {
			throw new NoSuchUserException();
		} catch (NonUniqueResultException e) {
			throw new RuntimeException();
		}

		if (!user.checkPassword(password)) {
		 		throw new NoSuchUserException();
		}

		return user;
	}
}
