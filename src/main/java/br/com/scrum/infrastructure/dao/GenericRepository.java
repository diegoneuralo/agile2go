package br.com.scrum.infrastructure.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.exception.ConstraintViolationException;

public class GenericRepository<T, IDType extends Serializable> {
				
	private Class<T> clazz;
	private EntityManager em;
	
	public GenericRepository (Class<T> clazz, EntityManager em) {		
		this.clazz = clazz;
		this.em = em;
	}
	
	public T merge (T obj) throws PersistenceException, ConstraintViolationException {		
		try {
			begin();
			obj = em.merge(obj);
			commit();
		} catch ( PersistenceException pe ) {
			rollback();
			throw pe;			
		} catch ( ConstraintViolationException cve ) {
			rollback();
			throw cve;
		}
		return obj;
	}

	public T persist (T obj) throws PersistenceException, ConstraintViolationException {		
		try {
			begin();
			em.persist(obj);
			commit();
			em.refresh(obj);
		} catch (PersistenceException pe) {
			rollback();
			throw pe;
		} catch ( ConstraintViolationException pe ) {
			rollback();
			throw pe;			
		}
		return obj;
	}

	public void remove (T obj) {		
		try {
			begin();
			obj = em.merge(obj);
			em.remove(obj);
			commit();
		} catch ( RuntimeException re ) {
			rollback();
			re.printStackTrace();
		}
	}

	public T find (IDType id) {		
		return em.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> list () {		
		Query query = em.createQuery("SELECT obj FROM " + clazz.getSimpleName() + " obj");
		return (List<T>) query.getResultList();		
	}

	@SuppressWarnings("unchecked")
	public List<T> listByNamedQuery (String queryName, Map<String, Object> params) {		
		Query query = em.createNamedQuery(queryName);
		if ( params != null && !params.isEmpty() ) 
			for ( String key : params.keySet() ) 
				query.setParameter(key, params.get(key));							
		return (List<T>) query.getResultList();		 
	}

	@SuppressWarnings("unchecked")
	public T getByNamedQuery (String queryName, Map<String, Object> params) {		
		Query query = em.createNamedQuery(queryName);				
		if ( params != null && !params.isEmpty() || params.size() != 0 ) 
			for ( String key : params.keySet() ) 
				query.setParameter(key, params.get(key));					
		return (T) query.getSingleResult();		
	}		
	
	private void begin () {
        if ( !em.getTransaction().isActive() )
        	em.getTransaction().begin();        
    }
    
    private void commit () {
        if ( em.getTransaction().isActive() ) 
        	em.getTransaction().commit();        
    }
    
    private void rollback () {
        if ( em.getTransaction().isActive() )
        	em.getTransaction().rollback();        
    }		

}
