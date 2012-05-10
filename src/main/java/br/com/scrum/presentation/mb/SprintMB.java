package br.com.scrum.presentation.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.exception.ConstraintViolationException;

import br.com.scrum.domain.entity.Sprint;
import br.com.scrum.domain.repository.SprintRepository;

@Named
@RequestScoped
public class SprintMB extends BaseBean implements Serializable {
			
	@Inject private SprintRepository sprintRepository;	
	private Sprint sprint = new Sprint();
	private List<Sprint> sprints;
	
	public void saveOrUpdate () {
		try {
			if ( sprint.getId() == 0 ) {					
				sprintRepository.save(sprint);
				sprint = new Sprint();
				addInfoMessage("sprint successfully created");
			} else {
				sprintRepository.update(sprint);
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
	
	public void cancel () {
		sprint = new Sprint();
	}	
	
	public List<Sprint> getSprints () {
		return ( sprints == null ? sprintRepository.findAll() : sprints );
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
