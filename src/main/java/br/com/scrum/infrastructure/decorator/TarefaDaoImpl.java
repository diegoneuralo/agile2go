package br.com.scrum.infrastructure.decorator;

import java.util.List;

import br.com.scrum.domain.entity.Tarefa;
import br.com.scrum.infrastructure.dao.Entity;
import br.com.scrum.infrastructure.dao.GenericRepository;
import br.com.scrum.infrastructure.repository.TarefaDao;

public class TarefaDaoImpl implements TarefaDao {
	
	private Entity<Tarefa, Integer> tarefaDao = new GenericRepository<Tarefa, Integer>(Tarefa.class);

	@Override
	public Tarefa salva (Tarefa tarefa) {
		return ( tarefa.getId() != 0 ? tarefaDao.merge(tarefa) : tarefaDao.persist(tarefa) );
	}

	@Override
	public void remove (Tarefa tarefa) {
		tarefaDao.remove(tarefa);
	}

	@Override
	public Tarefa comId (int id) {
		return tarefaDao.find(id);
	}

	@Override
	public List<Tarefa> todasTarefas () {
		return tarefaDao.list();
	}

	@Override
	public void fecha () {
		tarefaDao.close();
	}
	
	
}
