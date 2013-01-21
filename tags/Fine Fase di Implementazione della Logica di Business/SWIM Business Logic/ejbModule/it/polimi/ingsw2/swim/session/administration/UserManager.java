package it.polimi.ingsw2.swim.session.administration;

import java.util.List;

import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.NotificationManager;
import it.polimi.ingsw2.swim.session.remote.UserManagerRemote;
import it.polimi.ingsw2.swim.util.DAO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * Session Bean implementation class UserManager
 */
@Stateless
public class UserManager implements UserManagerRemote {

	private static final EntityManager em = DAO.getInstance().getEntityManager();
	
    /**
     * Default constructor. 
     */
    public UserManager() {
       super();
    }
    
    @SuppressWarnings("unchecked")
    @Override
	public List<User> retriveUserList(){
    	return em.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public User retriveUserProfile(String userId) throws NoSuchUserException{
		try {
			return (User) em.createQuery("SELECT u FROM User u join fetch u.abilities join fetch u.friendships join fetch u.notifications WHERE u.id =:id").setParameter("id", userId).getSingleResult();
		} catch (NoResultException e) {
			throw new NoSuchUserException();
		}
    }

    @Override
    public void sendMessage(String userId, String text) throws NoSuchUserException{
    	new NotificationManager().sendNotification(userId, "Il team amministrativo ti ha scritto: \"" + text +"\"");
    }
    
    @Override
    public void banUser(String userId){
    	User user = em.find(User.class, Long.parseLong(userId));
    	if(user != null){
    		user.ban();
    		em.merge(user);
    	}
    }
}
