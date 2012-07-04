package br.com.scrum.domain.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.exception.ConstraintViolationException;
import org.jboss.solder.logging.Logger;

import br.com.scrum.application.util.Assert;
import br.com.scrum.domain.entity.Sprint;
import br.com.scrum.infrastructure.dao.PersistenceUtil;

public class SprintService extends PersistenceUtil implements Serializable {
	
	@Inject private Logger logger;
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	public void create(Sprint sprint) {
		try {
			super.create(sprint);			
		} catch ( ConstraintViolationException cve ) {
			logger.error(cve.getCause().getLocalizedMessage());
			throw cve;
		}
	}
	
	public void save(Sprint sprint) {
		try {
			super.save(sprint);			
		} catch ( ConstraintViolationException cve) {
			logger.error(cve.getCause().getLocalizedMessage());
			throw cve;
		}
	}
	
	public void delete(Sprint sprint) {
		super.delete(getEntityManager().getReference(Sprint.class, sprint.getId()));			
	}

	public Sprint withId(Integer id) {
		return super.findById(Sprint.class, id);
	}

	public List<Sprint> findAll() {
		return super.findAll(Sprint.class);
	}

	public List<Sprint> searchBy(String query) {
		Assert.notNull(query, "query was null");
		
		try {
			return super.findByNamedQuery("Sprint.getByName", query);
		} catch (NoResultException nre) {
			logger.error("No sprint found with paramters [" + query + "]", nre);
		} catch (Exception e) {
			logger.error("Error fetching a sprint " + e.getCause().getLocalizedMessage());
		}
		return null;
	}
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	private static final long serialVersionUID = 7484077875891258960L;

}
