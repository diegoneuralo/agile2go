package br.com.scrum.domain.service;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.exception.ConstraintViolationException;

import br.com.scrum.domain.entity.Task;
import br.com.scrum.domain.qualifiers.TaskDI;
import br.com.scrum.domain.repository.TaskRepository;
import br.com.scrum.infrastructure.dao.GenericRepository;
import br.com.scrum.infrastructure.dao.exception.BusinessException;

public class TaskService implements TaskRepository {
	
	@Inject @TaskDI private GenericRepository<Task, Integer> taskRepository;

	@Override
	public Task save (Task task) {
		try {
			return taskRepository.persist(task);				
		} catch ( ConstraintViolationException cve ) {
			throw cve;	
		}
	}

	@Override
	public Task update (Task task) {
		try {
			return taskRepository.merge(task);				
		} catch ( ConstraintViolationException cve ) {
			throw cve;	
		}
	}

	@Override
	public Task withId (int id) {
		return taskRepository.find(id);
	}

	@Override
	public List<Task> findAll () {
		return taskRepository.list();
	}

	@Override
	public void remove(Task task) throws BusinessException {
		try {
			taskRepository.remove(task);					
		} catch (Exception e) {
			throw new BusinessException("error removing the task");
		}
	}	
	
}
