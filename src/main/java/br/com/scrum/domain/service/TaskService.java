package br.com.scrum.domain.service;

import java.util.List;

import javax.inject.Inject;

import br.com.scrum.domain.entity.Task;
import br.com.scrum.domain.qualifiers.TaskDI;
import br.com.scrum.domain.repository.TaskRepository;
import br.com.scrum.infrastructure.dao.GenericRepository;

public class TaskService implements TaskRepository {
	
	@Inject @TaskDI private GenericRepository<Task, Integer> tarefaDao;

	@Override
	public Task salva (Task task) {
		return ( task.getId() != 0 ? tarefaDao.merge(task) : tarefaDao.persist(task) );
	}

	@Override
	public void remove (Task task) {
		tarefaDao.remove(task);
	}

	@Override
	public Task comId (int id) {
		return tarefaDao.find(id);
	}

	@Override
	public List<Task> todasTarefas () {
		return tarefaDao.list();
	}	
	
}
