package br.com.scrum.infrastructure.repository;

import java.io.Serializable;
import java.util.List;

import br.com.scrum.domain.entity.Project;

public interface ProjectDao extends Serializable {
	
	Project save (Project project) throws Exception;
    
	void remove (Project project);
    
	Project withId (int id);
	
	List<Project> findAll ();
	
	void close ();
	
}
