package br.com.scrum.domain.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;

import br.com.scrum.domain.entity.Project;
import br.com.scrum.domain.qualifiers.ProjectDI;
import br.com.scrum.domain.repository.ProjectRepository;
import br.com.scrum.infrastructure.dao.GenericRepository;

public class ProjectService implements ProjectRepository, Serializable {			

	@Inject @ProjectDI private GenericRepository<Project, Integer> repository;

	@Override
	public Project save (Project project) throws PersistenceException, ConstraintViolationException {
		try {
			return repository.persist(project) ;
		} catch ( PersistenceException pe ) {
			throw pe;			
		} catch ( ConstraintViolationException cve ) {
			throw cve;	
		}
	}
	
	@Override
	public Project update (Project project) throws PersistenceException, ConstraintViolationException {
		try {
			return repository.merge(project) ;
		} catch ( PersistenceException pe ) {
			throw pe;			
		} catch ( ConstraintViolationException cve ) {
			throw cve;	
		}
	}

	@Override
	public void remove (Project project) throws Exception {		
		try {
			repository.remove(project);			
		} catch ( Exception e ) {
			throw e;
		}
	}

	@Override
	public Project withId (int id) {
		return repository.find(id);
	}

	@Override
	public List<Project> findAll () {
		return repository.list();
	}		

	private static final long serialVersionUID = 973523347646521301L;

}
