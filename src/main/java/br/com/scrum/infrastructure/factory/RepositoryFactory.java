package br.com.scrum.infrastructure.factory;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.scrum.domain.entity.Project;
import br.com.scrum.domain.entity.Sprint;
import br.com.scrum.domain.entity.Task;
import br.com.scrum.domain.entity.User;
import br.com.scrum.domain.qualifiers.ProjectDI;
import br.com.scrum.domain.qualifiers.SprintDI;
import br.com.scrum.domain.qualifiers.TaskDI;
import br.com.scrum.domain.qualifiers.UserDI;
import br.com.scrum.infrastructure.dao.GenericRepository;

public class RepositoryFactory {

	@Inject EntityManager em;

	@UserDI
	@Produces
	@SuppressWarnings("rawtypes")
	public GenericRepository createUserDao () {
		return new GenericRepository<User, Integer>(User.class, em);		
	}
	
	@ProjectDI
	@Produces
	@SuppressWarnings("rawtypes")
	public GenericRepository createProjectDao () {
		return new GenericRepository<Project, Integer>(Project.class, em);		
	}
	
	@SprintDI
	@Produces
	@SuppressWarnings("rawtypes")
	public GenericRepository createSprintDao () {
		return new GenericRepository<Sprint, Integer>(Sprint.class, em);		
	}
		
	@TaskDI
	@Produces
	@SuppressWarnings("rawtypes")
	public GenericRepository createTaskDao () {
		return new GenericRepository<Task, Integer>(Task.class, em);		
	}

}
