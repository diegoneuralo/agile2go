package br.com.scrum.presentation.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.exception.ConstraintViolationException;

import br.com.scrum.domain.entity.Project;
import br.com.scrum.domain.entity.Sprint;
import br.com.scrum.domain.service.ProjectService;
import br.com.scrum.domain.service.SprintService;
import br.com.scrum.infrastructure.dao.exception.BusinessException;

@Named
@RequestScoped
public class SprintMB extends BaseBean implements Serializable {
			
	@Inject private SprintService sprintService;	
	@Inject private ProjectService projectService;
	
	private Sprint sprint = new Sprint();	
	
	private List<Sprint> sprints;
	private List<Project> projects;		
	
	public void saveOrUpdate () {
		try {
			if ( sprint.getId() == null ) {					
				sprintService.save(sprint);
				sprint = new Sprint();
				addInfoMessage("sprint successfully created");				
			} else {
				sprintService.update(sprint);
				sprint = new Sprint();
				addInfoMessage("sprint successfully updated");
			}
		} catch ( ConstraintViolationException cve ) {
			addErrorMessage("sprint already exsists");
			cve.getCause().getLocalizedMessage();
		} catch ( Exception e ) {
			addErrorMessage("a excepted has ocurred");
			e.getCause().getLocalizedMessage();
		}
	}
	
	public void remove () {		
		try {
			sprintService.remove(sprint);
		} catch (Exception e) {
			e.getCause().getMessage();
			addErrorMessage(e.getMessage());
		}		
	}

	public List<Project> completeProject (String query) {
		try {
			if ( projects == null ) {
				projects = new ArrayList<Project>();
			}
			return projectService.searchBy(query);			
		} catch (BusinessException be) {
			be.getCause().getMessage();
			addInfoMessage(be.getMessage());
		} catch (Exception e) {
			addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return projects = new ArrayList<Project>();
	}
	
	public List<Sprint> getSprints() {
		return sprints == null ? sprints = sprintService.findAll() : sprints;		 
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	public void setSprints(List<Sprint> sprints) {
		this.sprints = sprints;
	}	

	private static final long serialVersionUID = 6429842878070655145L;

}
