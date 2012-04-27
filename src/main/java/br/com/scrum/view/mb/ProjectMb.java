package br.com.scrum.view.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.ConstraintViolationException;

import br.com.scrum.domain.entity.Project;
import br.com.scrum.infrastructure.decorator.ProjectDaoImpl;
import br.com.scrum.infrastructure.repository.ProjectDao;
import br.com.scrum.view.util.JsfUtil;

@Named
@RequestScoped
public class ProjectMb implements Serializable {
	
	private static final long serialVersionUID = 844308314603679973L;
	
	Project project = new Project();
	ProjectDao projectDao = new ProjectDaoImpl();
	List<Project> projects = new ArrayList<Project>();	
	
	public String addProject () {
		try {						
			projectDao.save(project);
			return "/pages/project/adiciona_sprint.jsf?faces-redirect=true";
		} catch ( ConstraintViolationException cve ) {
			cve.getCause().getLocalizedMessage();		
			JsfUtil.addErrorMessage("project already exist");
			return "";
		} catch ( Exception e ) {
			JsfUtil.addErrorMessage("unexcepted error has error");
			e.getCause().getLocalizedMessage();
			return "";
		}
	}
	
	public void cancelProject () {
		project.setDataEntrega(null);
		project.setDescricao(null);
		project.setEmpresa(null);
		project.setNome(null);
		project = new Project();
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Project> getProjects() {
		return projects == null ? new ArrayList<Project>() : projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
		
}
