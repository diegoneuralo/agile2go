package br.com.scrum.infrastructure.repository;

import java.util.List;

import br.com.scrum.domain.entity.Sprint;

public interface SprintDao {
	
	Sprint salva (Sprint sprint); 
	
	void remove (Sprint sprint);
	
	Sprint comId (int id);
	
	List<Sprint> todosSprints ();
	
	void fecha ();

}
