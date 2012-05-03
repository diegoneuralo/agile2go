package br.com.scrum.view.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolationException;

import br.com.scrum.domain.entity.Project;
import br.com.scrum.infrastructure.decorator.ProjectDaoImpl;
import br.com.scrum.infrastructure.repository.ProjectDao;

@Named
@RequestScoped
public class ProjectMb extends BaseBean implements Serializable {		
	
	private Project project;
	@Inject private ProjectDao projectDao;
	private List<Project> projects = new ArrayList<Project>();	
		
	public void addProject () {
		try {			
			projectDao.save(project);			
			addInfoMessage("project created successfully");
		} catch ( ConstraintViolationException cve ) {
			cve.getCause().getLocalizedMessage();		
			addErrorMessage("project already exist");			
		} catch ( Exception e ) {
			addErrorMessage("unexcepted error has ocurred");
			e.getCause().getLocalizedMessage();
		}
	}
		
	public void cancelProject () {		
		project = new Project();
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Project> getProjects() {
		return ( projects == null ? projectDao.findAll() : projects );
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	private static final long serialVersionUID = 844308314603679973L;
		
}
