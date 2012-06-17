package br.com.scrum.domain.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.exception.ConstraintViolationException;

import br.com.scrum.domain.entity.Project;
import br.com.scrum.infrastructure.dao.GenericRepository;
import br.com.scrum.infrastructure.dao.exception.BusinessException;

public class ProjectService implements Serializable {			
	
	@Inject private EntityManager em;
	@Inject private GenericRepository<Project, Integer> repository;
	
	public Project save (Project project) {
		try {
			return repository.persist(project) ;			
		} catch ( ConstraintViolationException cve ) {
			throw cve;	
		}
	}
	
	public Project update (Project project) {
		try {
			return repository.merge(project) ;				
		} catch ( ConstraintViolationException cve ) {
			throw cve;	
		}
	}

	public void remove (Project project) throws Exception {
		try {
			repository.remove(project);			
		} catch (Exception e) {
			throw e;
		}
	}

	public Project withId (Integer id) {
		return repository.find(id);
	}

	public List<Project> findAll () {
		return repository.list();
	}		

	public List<Project> searchBy (String query) throws BusinessException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Project.NAME, "%" +query.toUpperCase()+ "%");
		try {
			return repository.listByNamedQuery("Project.getByName", params);
		} catch ( NoResultException nre ) {
			throw new BusinessException("project not found");
		}	
	}
	
	/**
	 * this method set a external EntityManager, just for tests 
	 */
	public ProjectService setEm (EntityManager em) {
		this.em = em;
		repository = new GenericRepository<Project, Integer>(Project.class, em);
		return this;		
	}
	
	private static final long serialVersionUID = 973523347646521301L;
}
