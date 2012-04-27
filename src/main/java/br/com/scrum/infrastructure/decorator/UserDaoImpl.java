package br.com.scrum.infrastructure.decorator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.NoResultException;
import javax.validation.ReportAsSingleViolation;

import br.com.scrum.domain.entity.User;
import br.com.scrum.infrastructure.dao.Dao;
import br.com.scrum.infrastructure.dao.GenericDao;
import br.com.scrum.infrastructure.dao.exception.BusinessException;
import br.com.scrum.infrastructure.repository.UserDao;

public class UserDaoImpl implements UserDao, Serializable {

	private static final long serialVersionUID = 7103375420674460345L;

	private Dao<User, Integer> userDao = new GenericDao<User, Integer>(User.class);

	public User save (User user) throws Exception {
		try {
			return ( user.getId() != 0 ? userDao.merge(user) : userDao.persist(user) );
		} catch ( Exception e ) {
			throw e;
		}		
	}

	public void remove (User user) throws Exception {
		try {
			user = userDao.find(user.getId());
			userDao.remove(user);			
		} catch ( Exception e ) {
			throw e;
		}
	}

	public User withId (int id) {
		return userDao.find(id);
	}

	public User withLogin (String login, String password) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(User.LOGIN, login);
		params.put(User.SENHA, password);		
		try {
			return userDao.getByNamedQuery("User.getByLogin", params);
		} catch ( Exception e ) {
			throw e;
		}
	}

	public List<User> findAll () throws Exception {
		try {
			return userDao.list();
		} catch ( Exception e ) {
			throw e;
		}		
	}

}
