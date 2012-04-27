package br.com.scrum.infrastructure.decorator;

import java.util.List;

import br.com.scrum.domain.entity.Task;
import br.com.scrum.infrastructure.dao.Dao;
import br.com.scrum.infrastructure.dao.GenericDao;
import br.com.scrum.infrastructure.repository.TaskDao;

public class TaskDaoImpl implements TaskDao {
	
	private Dao<Task, Integer> tarefaDao = new GenericDao<Task, Integer>(Task.class);

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

	@Override
	public void fecha () {
		tarefaDao.close();
	}
	
	
}
