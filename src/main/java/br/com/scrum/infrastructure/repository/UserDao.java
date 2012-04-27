package br.com.scrum.infrastructure.repository;

import java.io.Serializable;
import java.util.List;

import br.com.scrum.domain.entity.User;

public interface UserDao extends Serializable {
	
	User save (User u) throws Exception;
	
	void remove (User u) throws Exception;
    
	User withId (int id);
    
	User withLogin (String login, String password) throws Exception;
    
	List<User> findAll () throws Exception;

}
