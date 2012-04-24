package br.com.scrum.view.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.scrum.domain.entity.Usuario;
import br.com.scrum.infrastructure.decorator.UsuarioDaoImpl;
import br.com.scrum.infrastructure.repository.UsuarioDao;
import br.com.scrum.view.util.JsfUtil;

@ManagedBean
@RequestScoped 
public class UsuarioMb implements Serializable {

	private static final long serialVersionUID = -3651540800634544658L;

	private final String PAGINA_PRINCIPAL = "/principal/principal.jsf?faces-redirect=true";		
	private Usuario usuario = new Usuario();	
	private UsuarioDao dao = new UsuarioDaoImpl();
	private String login;
	private String senha;

	public String loginUsuario () {
		try {			
			usuario = dao.comLogin(login, senha);			
			return PAGINA_PRINCIPAL;			
		} catch ( Exception e ) {
			JsfUtil.addErrorMessage("usuário não encontrado");
			return "";		
		}
	}

	public final Usuario getUsuario() {
		return usuario;
	}

	public final void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public final String getLogin() {
		return login;
	}

	public final void setLogin(String login) {
		this.login = login;
	}

	public final String getSenha() {
		return senha;
	}

	public final void setSenha(String senha) {
		this.senha = senha;
	}


}
