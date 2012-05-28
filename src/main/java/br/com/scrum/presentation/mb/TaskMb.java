package br.com.scrum.presentation.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.exception.ConstraintViolationException;

import br.com.scrum.domain.entity.Sprint;
import br.com.scrum.domain.entity.Task;
import br.com.scrum.domain.service.SprintService;
import br.com.scrum.domain.service.TaskService;
import br.com.scrum.infrastructure.dao.exception.BusinessException;

@Named
@RequestScoped
public class TaskMb extends BaseBean implements Serializable {
	
	@Inject TaskService taskService;
	@Inject SprintService sprintService;
	
	private Task task = new Task();
	
	private List<Task> tasks;
	private List<Sprint> sprints;
	
	public void saveOrUpdate () {
		try {
			if ( task.getId() == null ) {
				taskService.save(task);
				task = new Task();
				addInfoMessage("task successfully created");
			} else {
				taskService.update(task);
				task = new Task();
				addInfoMessage("task successfully updated");
			}
		} catch ( ConstraintViolationException cve ) {
			cve.getCause().getLocalizedMessage();		
			addInfoMessage("task already exist");			
		} catch ( Exception e ) {
			addErrorMessage("unexcepted error has ocurred");
			e.getCause().getLocalizedMessage();
		}			
	}
	
	public void remove () {		
		try {
			taskService.remove(task);
		} catch (Exception e) {
			e.getCause().getMessage();
			addErrorMessage(e.getMessage());
		}		
	}
	
	public List<Sprint> completeSprint (String query) {
		try {
			if ( sprints == null ) {
				sprints = new ArrayList<Sprint>();
			}
			return sprintService.searchBy(query);			
		} catch ( BusinessException be ) {
			be.getCause().getMessage();
			addInfoMessage(be.getMessage());
		} catch ( Exception e ) {
			e.getCause().getMessage();
			addErrorMessage(e.getMessage());
		}
		return sprints = new ArrayList<Sprint>();
	}				

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public List<Task> getTasks() {
		return tasks == null ? tasks = taskService.findAll() : tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	private static final long serialVersionUID = 8297152244005721364L;
	
}
