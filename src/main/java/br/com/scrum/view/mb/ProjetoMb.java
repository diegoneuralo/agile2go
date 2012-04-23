package br.com.scrum.view.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.validation.ConstraintViolationException;

import br.com.scrum.domain.entity.Projeto;
import br.com.scrum.infrastructure.decorator.ProjetoDaoImpl;
import br.com.scrum.infrastructure.repository.ProjetoDao;
import br.com.scrum.view.util.JsfUtil;

@ManagedBean
@SessionScoped
public class ProjetoMb implements Serializable {
	
	private static final long serialVersionUID = 844308314603679973L;
	
	Projeto projeto = new Projeto();
	ProjetoDao projetoDao = new ProjetoDaoImpl();
	List<Projeto> projetos = new ArrayList<Projeto>();	
	
	public String adicionaProjeto () {
		try {						
			projetoDao.salva(projeto);
			return "/pages/projeto/adiciona_sprint.jsf?faces-redirect=true";
		} catch ( ConstraintViolationException cve ) {
			cve.getCause().getLocalizedMessage();		
			JsfUtil.addErrorMessage("Projeto já existe!");
			return "";
		} catch ( Exception e ) {
			JsfUtil.addErrorMessage("Erro ao salvar projeto!");
			e.getCause().getLocalizedMessage();
			return "";
		}
	}
	
	public List<Projeto> listProjetos () {
		return projetos == null ? new ArrayList<Projeto>() : projetoDao.listaTodos(); 		
	}

	public final Projeto getProjeto() {
		return projeto;
	}

	public final void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public final List<Projeto> getProjetos() {
		return projetos;
	}

	public final void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}
	
}
