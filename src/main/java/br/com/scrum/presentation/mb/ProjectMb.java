package br.com.scrum.presentation.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.exception.ConstraintViolationException;

import br.com.scrum.domain.entity.Project;
import br.com.scrum.domain.service.ProjectService;

@Named
@SessionScoped
public class ProjectMb extends BaseBean implements Serializable {		

	@Inject ProjectService projectService;
	private Project project;
	private List<Project> projects;
	
	@PostConstruct
	public void init () {
		projects = projectService.findAll();
		project = new Project();
	}

	public void saveOrUpdate () {
		try {			
			if ( project.getId() == null ) {
				projectService.save(project);
				project = new Project();
				addInfoMessage("project successfully created");
			} else {
				projectService.update(project);
				project = new Project();
				addInfoMessage("project successfully updated");
			}
		} catch ( ConstraintViolationException cve ) {
			cve.getCause().getLocalizedMessage();		
			addErrorMessage("project already exist");			
		} catch ( Exception e ) {
			addErrorMessage("unexcepted error has ocurred");
			e.getCause().getLocalizedMessage();
		}
	}
	
	public void remove () {		
		try {
			projectService.remove(project);			
		} catch (Exception e) {
			e.getCause().getMessage();
			addErrorMessage(e.getMessage());
		}		
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	private static final long serialVersionUID = 844308314603679973L;

}
