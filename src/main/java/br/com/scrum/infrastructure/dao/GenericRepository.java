package br.com.scrum.infrastructure.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import br.com.scrum.domain.enums.Const;

public class GenericRepository	<T, IDTipo extends Serializable> implements Entity<T, IDTipo>, Serializable {

	private static final long serialVersionUID = 662696138506799273L;

	private static final String UNIDADE_PERSISTENCIA = Const.SCHEMA;
	@PersistenceUnit private  EntityManagerFactory emf;	
	@PersistenceContext private EntityManager em;
	private Class<T> classePersistente;

	public GenericRepository (Class<T> clazz) {
		classePersistente = clazz;
	}

	@Produces
	protected EntityManager getEntityManager() {		
		if ( emf == null || !emf.isOpen() )
			emf = Persistence.createEntityManagerFactory(UNIDADE_PERSISTENCIA);
		if ( em != null && em.isOpen() )
			return em;
		return emf.createEntityManager();		

	}

	public T merge(T obj) {
		em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			obj = em.merge(obj);
			tx.commit();
		} catch ( PersistenceException pe ) {
			tx.rollback();
			pe.printStackTrace();
		}
		return obj;

	}

	public T persist(T obj) {
		em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(obj);
			tx.commit();
			em.refresh(obj);
		} catch ( PersistenceException pe ) {
			tx.rollback();
			pe.printStackTrace();
		}
		return obj;
	}

	public void remove(T obj) {
		em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			obj = em.merge(obj);
			em.remove(obj);
			tx.commit();
		} catch ( RuntimeException re ) {
			tx.rollback();
			re.printStackTrace();
		}
	}

	public T find(IDTipo id) {
		em = getEntityManager();
		return em.find(classePersistente, id);

	}

	@SuppressWarnings("unchecked")
	public List<T> list () {
		em = getEntityManager();
		Query query = em.createQuery("SELECT obj FROM " + classePersistente.getSimpleName() + " obj");
		List<T> list = query.getResultList();
		return list;
	}

	public List<T> listByNamedQuery (String queryName, Map<String, Object> params) {
		em = getEntityManager();
		Query query = em.createNamedQuery(queryName);
		if ( params != null && !params.isEmpty() ) {
			for ( String key : params.keySet() ) {
				query.setParameter(key, params.get(key));
			}
		}
		@SuppressWarnings("unchecked")
		List<T> list = query.getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	public T getByNamedQuery (String queryName, Map<String, Object> params) {
		em = getEntityManager();		
		Query query = em.createNamedQuery(queryName);				
		if ( params != null && !params.isEmpty() || params.size() != 0 ) {
			for ( String key : params.keySet() ) {
				query.setParameter(key, params.get(key));
			}
		}			
		return (T) query.getSingleResult();		

	}

	public void close() {
		if ( em != null && em.isOpen() ) {
			em.close();
		}
	}

}
