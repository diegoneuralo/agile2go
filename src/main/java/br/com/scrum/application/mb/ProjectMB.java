package br.com.scrum.application.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.exception.ConstraintViolationException;

import br.com.scrum.domain.entity.Project;
import br.com.scrum.domain.service.ProjectService;

@Named
@ViewScoped
public class ProjectMB extends BaseBean implements Serializable {		

	@Inject private ProjectService projectService;
	
	private Project project = new Project();
	private List<Project> projects;
	
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
			projects = projectService.findAll();
		} catch ( ConstraintViolationException cve ) {
			addErrorMessage(null, "project already exist");			
			cve.printStackTrace();		
		} catch ( Exception e ) {
			addErrorMessage("unexcepted error has ocurred");
			e.printStackTrace();
		}
	}

	public void remove () {		
		try {
			projectService.remove(project);	
			project = new Project();
			projects = projectService.findAll();
			addInfoMessage("project removed");
		} catch ( Exception e ) {
			addErrorMessage(e.getMessage());
			e.printStackTrace();
		}		
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Project> getProjects() {		              
		return projects == null ?  projects = projectService.findAll() : projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	private static final long serialVersionUID = 844308314603679973L;

}
