package br.com.scrum.domain.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.exception.ConstraintViolationException;

import br.com.scrum.domain.entity.User;
import br.com.scrum.infrastructure.dao.PersistenceUtil;

public class UserService implements Serializable {	

	@Inject private EntityManager em;
	@Inject private PersistenceUtil<User, Integer> repository;

	public User save (User user) {
		try {
			return repository.persist(user);
		} catch ( ConstraintViolationException cve ) {
			throw cve;
		}		
	}
	
	public User update (User user) {
		try {
			return repository.persist(user);
		} catch ( ConstraintViolationException cve ) {
			throw cve;
		}		
	}

	public void remove (User user) {
		repository.remove(user);			
	}

	public User withId (int id) {
		return repository.find(id);
	}
	
	public List<User> findAll () throws Exception {
		return repository.list();
	}

	public User withLogin (String login, String password) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(User.LOGIN, login);
		params.put(User.PASSWORD, password);		
		try {
			return repository.getByNamedQuery("User.getByLogin", params);
		} catch ( NoResultException nre ) {
			throw nre;
		}
	}

	/**
	 * this method set a external EntityManager, just for tests 
	 */
	public UserService setEm (EntityManager em) {
		this.em = em;    
		repository = new PersistenceUtil<User, Integer>(User.class, em);
		return this;
	}

	private static final long serialVersionUID = 7103375420674460345L;

}
