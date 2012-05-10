package br.com.scrum.domain.repository;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import br.com.scrum.domain.entity.Sprint;

public interface SprintRepository {
	
	Sprint save (Sprint sprint) throws ConstraintViolationException;
	
	Sprint update (Sprint sprint) throws ConstraintViolationException;
	
	void remove (Sprint sprint);
	
	Sprint withId (int id);
	
	List<Sprint> findAll ();		

}
