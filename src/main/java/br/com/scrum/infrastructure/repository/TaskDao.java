package br.com.scrum.infrastructure.repository;

import java.util.List;

import br.com.scrum.domain.entity.Task;

public interface TaskDao {
	
	Task salva (Task task);
	
	void remove (Task task);

	Task comId (int id);
	
	List<Task> todasTarefas ();
	
	void fecha (); 
	
}
