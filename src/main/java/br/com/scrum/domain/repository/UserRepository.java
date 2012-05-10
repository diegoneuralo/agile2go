package br.com.scrum.domain.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import br.com.scrum.domain.entity.User;

public interface UserRepository extends Serializable {
	
	User save (User u) throws ConstraintViolationException, Exception;
	
	void remove (User u) throws Exception;
    
	User withId (int id);
    
	User withLogin (String login, String password) throws Exception;
    
	List<User> findAll () throws Exception;

}
