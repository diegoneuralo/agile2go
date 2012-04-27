package br.com.scrum.view.mb;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import br.com.scrum.domain.entity.User;
import br.com.scrum.infrastructure.decorator.UserDaoImpl;
import br.com.scrum.infrastructure.repository.UserDao;
import br.com.scrum.view.util.JsfUtil;
import br.com.scrum.view.util.Validate;

@Named
@RequestScoped
public class UserMb implements Serializable {

	private static final long serialVersionUID = -3651540800634544658L;	
	private final String PRINCIPAL_PAGE = "/principal/principal.jsf?faces-redirect=true";		
	private User user = new User();	
	private UserDao userDao = new UserDaoImpl();
	private String login;
	private String password;

	public String userLogin () {
		try {			
			user = userDao.withLogin(login, password);	
			Validate.notNull(user);
			return PRINCIPAL_PAGE;
		} catch ( NoResultException nre ) {
			nre.printStackTrace();
			JsfUtil.addErrorMessage("user not found");
			return "";
		} catch ( Exception e ) {
			e.printStackTrace();
			JsfUtil.addErrorMessage("unexcepted error has ocurred");
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
	
}
