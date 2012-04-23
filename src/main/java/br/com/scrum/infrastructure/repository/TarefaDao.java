package br.com.scrum.infrastructure.repository;

import java.util.List;

import br.com.scrum.domain.entity.Tarefa;

public interface TarefaDao {
	
	Tarefa salva (Tarefa tarefa);
	
	void remove (Tarefa tarefa);

	Tarefa comId (int id);
	
	List<Tarefa> todasTarefas ();
	
	void fecha (); 
	
}
