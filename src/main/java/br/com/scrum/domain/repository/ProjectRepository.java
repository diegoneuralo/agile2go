package br.com.scrum.domain.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import br.com.scrum.domain.entity.Project;

public interface ProjectRepository extends Serializable {
	
	Project save (Project project) throws ConstraintViolationException, Exception;
	
	Project update (Project project) throws ConstraintViolationException, Exception;
    
	void remove (Project project) throws Exception;
    
	Project withId (int id);
	
	List<Project> findAll ();		
	
}
