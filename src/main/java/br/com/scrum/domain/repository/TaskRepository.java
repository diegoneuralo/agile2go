package br.com.scrum.domain.repository;

import java.util.List;

import br.com.scrum.domain.entity.Task;

public interface TaskRepository {
	
	Task salva (Task task);
	
	void remove (Task task);

	Task comId (int id);
	
	List<Task> todasTarefas ();
	
}
