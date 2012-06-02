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
import br.com.scrum.infrastructure.dao.GenericRepository;
import br.com.scrum.infrastructure.dao.exception.BusinessException;

public class SprintService implements Serializable {
	
	@Inject private EntityManager em;
	@Inject private GenericRepository<Sprint, Integer> repository;
	
//	/**
//	 * this method set a external EntityManager, just for tests 
//	 */
//	public SprintService setEm (EntityManager em) {
//		this.em = em;
//		repository = new GenericRepository<Sprint, Integer>(Sprint.class, em);
//		return this;		
//	}
	
	public Sprint save (Sprint sprint) {
		try {
			return repository.persist(sprint);			
		} catch ( ConstraintViolationException cve ) {
			cve.getCause().getMessage();
			throw cve;
		}
	}
	
	public Sprint update (Sprint sprint) {
		try {
			return repository.merge(sprint);			
		} catch ( ConstraintViolationException cve) {
			cve.getCause().getMessage();
			throw cve;
		}
	}

	public void remove (Sprint sprint) {
		try {
			repository.remove(sprint);			
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	public Sprint withId (Integer id) {
		return repository.find(id);
	}

	public List<Sprint> findAll () {
		return (List<Sprint>) repository.list();
	}

	public List<Sprint> searchBy (String query) throws BusinessException,  Exception {
		if ( query.isEmpty() )
			throw new Exception("the sprint name can not be empty!");
				
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Sprint.NAME, "%" +query.toUpperCase()+ "%");
		try {
			return repository.listByNamedQuery("Sprint.getByName", params);
		} catch ( NoResultException nre ) {
			nre.getCause().getMessage();
			throw new BusinessException("sprint not found");
		}
	}
	
	private static final long serialVersionUID = 7484077875891258960L;

}
