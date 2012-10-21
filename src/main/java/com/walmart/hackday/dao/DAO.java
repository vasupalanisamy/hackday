
package com.walmart.hackday.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DAO {
	
	public static final DAO INSTANCE = new DAO();
	
	public final EntityManagerFactory emf;
	public final EntityManager em;	
	
	public DAO() {
		
		emf = Persistence.createEntityManagerFactory("hackday");
		em = emf.createEntityManager();	
	
	}

	public void clear() {
		em.clear();
	}
	public void close() {
		if(em != null && em.isOpen()) {
			em.close();
		}
		if(emf != null && emf.isOpen()) {
			emf.close();
		}
	}	
	public void save(Object o) {
		
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.persist(o);
		et.commit();
		
	}
	
	public <T> T get(Class<T> entityType, Object key) {
		
		return em.find(entityType, key);
		
	}
	
	public void update(Object o) {
		
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.merge(o);
		et.commit();
		
	}
	
	public void delete(Object o) {
		
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(o);
		et.commit();
		
	}
	
	@Override
	@SuppressWarnings("FinalizeDeclaration")
	protected void finalize() throws Throwable {
		
		super.finalize();
		if(em != null && em.isOpen()) {
			em.close();
		}
		if(emf != null && emf.isOpen()) {
			emf.close();
		}
	}
	
}
