package br.com.scrum.presentation.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.exception.ConstraintViolationException;

import br.com.scrum.domain.entity.Task;
import br.com.scrum.domain.repository.TaskRepository;

@Named
@RequestScoped
public class TaskMb extends BaseBean implements Serializable {
	
	@Inject TaskRepository taskRepository;
	private Task task = new Task();
	private List<Task> tasks;
	
	public void saveOrUpdate () {
		try {
			if ( task.getId() == 0 ) {
				taskRepository.save(task);
				task = new Task();
				addInfoMessage("task successfully created");
			} else {
				taskRepository.update(task);
				task = new Task();
				addInfoMessage("task successfully updated");
			}
		} catch ( ConstraintViolationException cve ) {
			cve.getCause().getLocalizedMessage();		
			addErrorMessage("task already exist");			
		} catch ( Exception e ) {
			addErrorMessage("unexcepted error has ocurred");
			e.getCause().getLocalizedMessage();
		}			
	}
	
	public void cancelTask () {
		task = new Task();
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public List<Task> getTasks() {
		return tasks == null ? taskRepository.findAll() : tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	private static final long serialVersionUID = 8297152244005721364L;
	
}
