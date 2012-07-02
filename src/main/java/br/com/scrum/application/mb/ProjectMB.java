package br.com.scrum.application.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.exception.ConstraintViolationException;
import org.jboss.solder.logging.Logger;

import br.com.scrum.domain.entity.Project;
import br.com.scrum.domain.service.ProjectService;

@Named
@ViewScoped
public class ProjectMB extends BaseBean implements Serializable {		
	
	private final Logger logger = Logger.getLogger(ProjectMB.class);
	@Inject private ProjectService projectService;
	
	private Project project = new Project();
	private List<Project> projects;
	
	public void createOrSave() {
		try {			
			if ( project.getId() == null ) {
				projectService.create(project);
				project = new Project();
				addInfoMessage("project successfully created");
			} else {
				projectService.save(project);
				addInfoMessage("project successfully updated");
			}
		} catch ( ConstraintViolationException cve ) {
			logger.error(cve);
			addErrorMessage(null, "project already exist");	
		} catch ( Exception e ) {
			logger.error(e);
			addErrorMessage("unexcepted error has ocurred");
		}
	}

	public void delete() {		
		try {
			projectService.delete(project);	
//			projects = projectService.findAll();
			addInfoMessage("project removed");
		} catch ( Exception e ) {
			logger.error(e);
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
		return projects == null ?  projects = projectService.findAll() : projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	private static final long serialVersionUID = 844308314603679973L;

}
