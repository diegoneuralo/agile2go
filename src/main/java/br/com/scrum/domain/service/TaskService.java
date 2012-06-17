package br.com.scrum.domain.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.exception.ConstraintViolationException;

import br.com.scrum.domain.entity.Task;
import br.com.scrum.infrastructure.dao.GenericRepository;

public class TaskService implements Serializable {
	
	@Inject private EntityManager em;
	@Inject private GenericRepository<Task, Integer> repository;
	
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

	public void remove(Task task) throws Exception {
		try {
			repository.remove(task);					
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * this method set a external EntityManager, just for tests 
	 */
	public TaskService setEm (EntityManager em) {
		this.em = em;
		repository = new GenericRepository<Task, Integer>(Task.class, em);
		return this;		
	}
	
	private static final long serialVersionUID = 9002969380414395854L;
	
}
