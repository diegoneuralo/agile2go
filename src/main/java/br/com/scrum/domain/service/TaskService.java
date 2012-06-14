package br.com.scrum.domain.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.exception.ConstraintViolationException;

import br.com.scrum.domain.entity.Sprint;
import br.com.scrum.domain.entity.Task;
import br.com.scrum.infrastructure.dao.GenericRepository;
import br.com.scrum.infrastructure.dao.exception.BusinessException;

public class TaskService implements Serializable {
	
	@Inject private EntityManager em;
	@Inject private GenericRepository<Task, Integer> repository;
	
//	/**
//	 * this method set a external EntityManager, just for tests 
//	 */
//	public TaskService setEm (EntityManager em) {
//		this.em = em;
//		repository = new GenericRepository<Task, Integer>(Task.class, em);
//		return this;		
//	}
	
	public Task save (Task task) {
		try {
			return repository.persist(task);				
		} catch ( ConstraintViolationException cve ) {
			throw cve;	
		}
	}
	
	public Task update (Task task) {
		try {
			return repository.merge(task);				
		} catch ( ConstraintViolationException cve ) {
			throw cve;	
		}
	}

	public Task withId (Integer id) {
		return repository.find(id);
	}

	public List<Task> findAll () {
		return repository.list();
	}

	public void remove(Task task) throws BusinessException {		
		repository.remove(task);					
	}
	
	private static final long serialVersionUID = 9002969380414395854L;
	
}
