package br.com.scrum.infrastructure.decorator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.jboss.shrinkwrap.impl.base.GenericArchiveImpl;

import br.com.scrum.domain.entity.User;
import br.com.scrum.infrastructure.dao.Dao;
import br.com.scrum.infrastructure.dao.GenericDao;
import br.com.scrum.infrastructure.repository.UserDao;
import br.com.scrum.view.util.DAO;

public class UserDaoImpl implements UserDao, Serializable {	

    private Dao<User, Integer> dao = new GenericDao<User, Integer>(User.class);       

	public User save (User user) throws Exception {
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
