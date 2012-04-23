package br.com.scrum.infrastructure.repository;

import java.util.List;

import br.com.scrum.domain.entity.Projeto;

public interface ProjetoDao {
	
	Projeto salva (Projeto projeto) throws Exception;
    
	void remove (Projeto projeto);
    
	Projeto comId (int id);
	
	List<Projeto> listaTodos ();
	
	void fecha ();
	
}
