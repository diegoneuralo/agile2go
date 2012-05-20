package br.com.scrum.domain.repository;

import java.util.List;

import br.com.scrum.domain.entity.Task;
import br.com.scrum.infrastructure.dao.exception.BusinessException;

public interface TaskRepository {
	
	Task save (Task task);
	
	Task update (Task taks);
	
	void remove (Task task) throws BusinessException;

	Task withId (int id);
	
	List<Task> findAll ();
	
}
