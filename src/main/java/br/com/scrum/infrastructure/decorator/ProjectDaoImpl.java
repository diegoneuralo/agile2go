package br.com.scrum.infrastructure.decorator;

import java.io.Serializable;
import java.util.List;

import br.com.scrum.domain.entity.Project;
import br.com.scrum.infrastructure.dao.Dao;
import br.com.scrum.infrastructure.dao.GenericDao;
import br.com.scrum.infrastructure.repository.ProjectDao;

public class ProjectDaoImpl implements ProjectDao, Serializable {			
	
	private Dao<Project, Integer> projectDao = new GenericDao<Project, Integer>(Project.class);;
		
	@Override
	public Project save (Project project) throws Exception {
		try {
			return (project.getId() != 0  ? projectDao.merge(project) : projectDao.persist(project));			
		} catch ( Exception e ) {
			throw e;
		}
	}

	@Override
	public void remove (Project project) {		
		projectDao.remove(project);
	}

	@Override
	public Project withId (int id) {
		return projectDao.find(id);
	}
	
	@Override
	public List<Project> findAll () {
		return projectDao.list();
	}
	
	@Override
	public void close () {
		projectDao.close();
	}
	
	private static final long serialVersionUID = 973523347646521301L;

}
