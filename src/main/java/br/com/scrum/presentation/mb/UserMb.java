package br.com.scrum.presentation.mb;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import br.com.scrum.domain.entity.User;
import br.com.scrum.domain.entity.enums.Const;
import br.com.scrum.domain.repository.UserRepository;

@Named
@RequestScoped
public class UserMb extends BaseBean implements Serializable {
			
	private User user;	
    @Inject private UserRepository userRepository;
	
	private String login;
	private String password;

	public String userLogin () {
		try {			
			user = userRepository.withLogin(login, password);				
			return Const.PRINCIPAL;
		} catch ( NoResultException nre ) {
			nre.printStackTrace();
			addErrorMessage("user not found");
			return "";
		} catch ( Exception e ) {
			e.getCause().getLocalizedMessage();
			addErrorMessage("unexcepted error has ocurred");
			return "";		
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	private static final long serialVersionUID = -3651540800634544658L;
	
}
