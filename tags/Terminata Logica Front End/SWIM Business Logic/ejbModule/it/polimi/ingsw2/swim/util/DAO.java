package it.polimi.ingsw2.swim.util;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class DAO {
	private static HashMap<String,DAO> persistenceContexts;
	
	private EntityManager entityManager;
	
	private DAO(){
		super();
	}
	
	@PersistenceContext(unitName = "persistentData")
	private void setEntityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	public EntityManager getEntityManager(){
		return entityManager;
	}
	
	public static DAO getInstance(){
		if(!persistenceContexts.containsKey(null)){
			persistenceContexts.put(null, new DAO());
		}
		return persistenceContexts.get(null);
	}
	
	public static DAO getInstance(String persistenceUnit){
		if(!persistenceContexts.containsKey(persistenceUnit)){
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
			EntityManager em = emf.createEntityManager();
			DAO dao = new DAO();
			dao.setEntityManager(em);
			persistenceContexts.put(persistenceUnit, dao);
		}
		return persistenceContexts.get(persistenceUnit);
	}
	
	public static DAO getInstance(String persistenceUnit, EntityManager em){
		if(!persistenceContexts.containsKey(persistenceUnit)){
			DAO dao = new DAO();
			dao.setEntityManager(em);
			persistenceContexts.put(persistenceUnit, dao);
		}
		return persistenceContexts.get(persistenceUnit);
	}
}
