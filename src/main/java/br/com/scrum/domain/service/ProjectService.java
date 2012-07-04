package br.com.scrum.domain.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.exception.ConstraintViolationException;
import org.jboss.solder.logging.Logger;

import br.com.scrum.application.util.Assert;
import br.com.scrum.domain.entity.Project;
import br.com.scrum.infrastructure.dao.PersistenceUtil;

public class ProjectService extends PersistenceUtil implements Serializable {			
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	protected EntityManager em;

	public void create(Project project) {
		try {
			super.create(project);			
		} catch ( ConstraintViolationException cve ) {
			logger.error(cve.getCause().getLocalizedMessage());
			throw cve;	
		}
	}
	
	public void save(Project project) {
		try {
			super.save(project);				
		} catch ( ConstraintViolationException cve ) {
			logger.error(cve.getCause().getLocalizedMessage());
			throw cve;	
		}
	}
	
	public void delete(Project project) {
		super.delete(getEntityManager().getReference(Project.class, project.getId()));				
	}
	
	public Project withId(Integer id) {
		return super.findById(Project.class, id);
	}
	
	public List<Project> findAll() {
		return super.findAll(Project.class);
	}		
	
	public List<Project> searchBy(String query) {
		Assert.notNull(query, "query was null");
		
		try {
			return super.findByNamedQuery("Project.getByName", query);
		} catch (NoResultException nre) {
			logger.error("No project found with paramters [" + query + "]", nre);
		} catch (Exception e) {
			logger.error("Error fetching the project " + e.getCause().getLocalizedMessage());
		}
		return null;
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	private static final long serialVersionUID = 973523347646521301L;
}
