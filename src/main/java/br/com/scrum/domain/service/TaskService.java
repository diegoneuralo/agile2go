package br.com.scrum.domain.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.exception.ConstraintViolationException;
import org.jboss.solder.logging.Logger;

import br.com.scrum.domain.entity.Task;
import br.com.scrum.infrastructure.dao.PersistenceUtil;

public class TaskService extends PersistenceUtil implements Serializable {
	
	@Inject private Logger logger;
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	public void create(Task task) {
		try {
			super.create(task);				
		} catch ( ConstraintViolationException cve ) {
			logger.error(cve.getCause().getLocalizedMessage());
			throw cve;	
		}
	}
	
	public void save(Task task) {
		try {
			super.save(task);				
		} catch ( ConstraintViolationException cve ) {
			logger.error(cve.getCause().getLocalizedMessage());
			throw cve;	
		}
	}
	
	public void delete(Task task) {
		super.delete(getEntityManager().getReference(Task.class, task.getId()));					
	}

	public Task withId(Integer id) {
		return super.findById(Task.class, id);
	}

	public List<Task> findAll() {
		return super.findAll(Task.class);
	}
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	private static final long serialVersionUID = 9002969380414395854L;
	
}
