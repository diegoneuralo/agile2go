package br.com.scrum.domain.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.exception.ConstraintViolationException;

import br.com.scrum.domain.entity.User;
import br.com.scrum.infrastructure.dao.GenericRepository;

public class UserService implements Serializable {	

	@Inject private EntityManager em;
    @Inject private GenericRepository<User, Integer> dao;
            
//    public UserService setEm (EntityManager em) {
//        this.em = em;    
//        dao = new GenericRepository<User, Integer>(User.class, em);
//        return this;
//    }

	public User save (User user) throws ConstraintViolationException, Exception {
		try {
			return ( user.getId() != 0 ? dao.merge(user) : dao.persist(user) );
		} catch ( Exception e ) {
			throw e;
		}		
	}

	public void remove (User user) throws Exception {
		try {
			user = dao.find(user.getId());
			dao.remove(user);			
		} catch ( Exception e ) {
			throw e;
		}
	}

	public User withId (int id) {
		return dao.find(id);
	}

	public User withLogin (String login, String password) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(User.LOGIN, login);
		params.put(User.PASSWORD, password);		
		try {
			return dao.getByNamedQuery("User.getByLogin", params);
		} catch ( Exception e ) {
			throw e;
		}
	}

	public List<User> findAll () throws Exception {
		try {
			return dao.list();
		} catch ( Exception e ) {
			throw e;
		}		
	}
	
	private static final long serialVersionUID = 7103375420674460345L;

}
