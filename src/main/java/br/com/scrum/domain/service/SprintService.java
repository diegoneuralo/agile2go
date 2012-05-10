package br.com.scrum.domain.service;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.exception.ConstraintViolationException;

import br.com.scrum.domain.entity.Sprint;
import br.com.scrum.domain.qualifiers.SprintDI;
import br.com.scrum.domain.repository.SprintRepository;
import br.com.scrum.infrastructure.dao.GenericRepository;

public class SprintService implements SprintRepository {
	
	@Inject @SprintDI private GenericRepository<Sprint, Integer> sprintDao;

	@Override
	public Sprint save (Sprint sprint) {
		try {
			return sprintDao.persist(sprint);			
		} catch ( ConstraintViolationException cve ) {
			throw cve;
		}
	}
	
	@Override
	public Sprint update (Sprint sprint) {
		try {
			return sprintDao.merge(sprint);			
		} catch ( ConstraintViolationException cve) {
			throw cve;
		}
	}

	@Override
	public void remove (Sprint sprint) {
		sprintDao.remove(sprint);
	}

	@Override
	public Sprint withId (int id) {
		return sprintDao.find(id);
	}

	@Override
	public List<Sprint> findAll () {
		return sprintDao.list();
	}

}
