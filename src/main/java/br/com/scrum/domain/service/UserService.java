package br.com.scrum.domain.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.jboss.seam.transaction.TransactionPropagation;
import org.jboss.seam.transaction.Transactional;
import org.jboss.solder.logging.Logger;

import br.com.scrum.domain.entity.User;
import br.com.scrum.infrastructure.dao.PersistenceUtil;

@Transactional(TransactionPropagation.REQUIRED)
public class UserService extends PersistenceUtil implements Serializable {
	
	@Inject private Logger logger;
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	public User getUserByIdentityKey(String username, String password) {
		try {
			return super.findUniqueByNamedQuery("User.getByLogin", username, password);
		} catch (NoResultException e) {
			logger.error("No user found for username [" + username + "]", e);
			return null;
		}
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	private static final long serialVersionUID = 4629428604267292464L;

}
