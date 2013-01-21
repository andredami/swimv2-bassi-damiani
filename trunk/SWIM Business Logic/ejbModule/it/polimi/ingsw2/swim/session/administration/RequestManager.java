package it.polimi.ingsw2.swim.session.administration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.entities.Alias;
import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.AlreadyActiveException;
import it.polimi.ingsw2.swim.exceptions.DuplicateAbilityException;
import it.polimi.ingsw2.swim.exceptions.DuplicateAliasException;
import it.polimi.ingsw2.swim.exceptions.NoSuchAbilityException;
import it.polimi.ingsw2.swim.exceptions.NoSuchRequestException;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.NotificationManager;
import it.polimi.ingsw2.swim.session.remote.RequestManagerRemote;
import it.polimi.ingsw2.swim.util.DAO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * Session Bean implementation class RequestManager
 */
@Stateless
public class RequestManager implements RequestManagerRemote {

	private static final EntityManager em = DAO.getInstance()
			.getEntityManager();

	/**
	 * Default constructor.
	 */
	public RequestManager() {
		super();
	}

	@Override
	public void addNewAbility(String name, String description,
			Set<String> alias, Set<String> stubAbilities)
			throws DuplicateAliasException, DuplicateAbilityException {
		if (name == null || description == null) {
			throw new IllegalArgumentException();
		}
		alias = alias != null ? alias : new HashSet<String>();
		for (String s : alias) {
			if (em.find(Alias.class, s.toLowerCase()) != null) {
				throw new DuplicateAliasException(s);
			}
		}

		Ability ability;
		try {
			ability = (Ability) em.createNamedQuery("getAbilityWithAlias")
					.setParameter("ability", name);
			if (!ability.isStub()) {
				throw new DuplicateAbilityException();
			}
			try {
				ability.confirm();
			} catch (AlreadyActiveException e) {
				throw new DuplicateAbilityException();
			}
			ability.setDescription(description);
			for (String s : alias) {
				ability.addAlias(s.toLowerCase());
			}
			em.merge(ability);
			stubAbilities.add(ability.getName());
		} catch (NoResultException e1) {
			ability = new Ability(name, description, alias);
			em.persist(ability);
		}

		addAbilityToSubscribers(stubAbilities, ability);
	}

	@Override
	public void addAbilityAsAlias(String abilityId, String name, Set<String> stubAbilities) throws DuplicateAliasException, NoSuchAbilityException, DuplicateAbilityException{
    	if(name == null || abilityId == null){
    		throw new IllegalArgumentException();
    	}
    	    	
    	Ability ability;
		try {
			ability = (Ability) em.createNamedQuery("getAbilityWithAlias")
					.setParameter("ability", abilityId);
		} catch (NoResultException e1) {
			throw new NoSuchAbilityException();
		}
		
		if (ability.isStub()) {
			try {
				ability.confirm();
			} catch (AlreadyActiveException e) {
				throw new DuplicateAbilityException();
			}
			stubAbilities.add(ability.getName());
		}
		
		if (em.find(Alias.class, name.toLowerCase()) != null) {
			throw new DuplicateAliasException();
		}
		ability.addAlias(name);
		em.merge(ability);

		addAbilityToSubscribers(stubAbilities, ability);
	}

	private void addAbilityToSubscribers(Set<String> stubAbilities,
			Ability ability) {
		NotificationManager nf = new NotificationManager();

		for (String s : stubAbilities) {
			Ability stub;
			try {
				stub = (Ability) em
						.createNamedQuery("getAbilityWithSubscriber")
						.setParameter("ability", s.toLowerCase())
						.getSingleResult();
			} catch (NoResultException e) {
				continue;
			}

			if (stub != null) {
				for (User u : stub.getSubscribers()) {
					User user = (User) em
							.createNamedQuery("getUserWithAbilities")
							.setParameter("id", u.getId()).getSingleResult();
					user.addAbility(ability);
					try {
						nf.sendNotification(
								user.getId().toString(),
								"L'abilità "
										+ stub.getName()
										+ " che avevi richiesto è stata accetta e aggiunta automaticamente al tuo profilo con il nome "
										+ ability.getName());
					} catch (NoSuchUserException e) {
						throw new RuntimeException();
					}
				}
				if (stub.isStub()) {
					em.remove(stub);
				}
			}
		}
	}
	
	@Override
	public void deleteRequest(String abilityId) throws NoSuchRequestException{
		Ability ability = em.find(Ability.class, abilityId.toLowerCase());
		if(ability != null && !ability.isStub()){
			throw new NoSuchRequestException();
		}
		em.remove(ability);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ability> retriveRequestsList(){
		return em.createQuery("SELECT r FROM Ability r WHERE r.isStub = TRUE").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ability> retriveRequestsList(String searchKey){
		searchKey = "%" + searchKey + "%";
		return em.createQuery("SELECT r FROM Ability r WHERE r.name LIKE ':key' AND r.isStub = TRUE").setParameter("key", searchKey).getResultList();
	}

}
