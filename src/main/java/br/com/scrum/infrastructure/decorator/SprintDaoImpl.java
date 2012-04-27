package br.com.scrum.infrastructure.decorator;

import java.util.List;

import br.com.scrum.domain.entity.Sprint;
import br.com.scrum.infrastructure.dao.Dao;
import br.com.scrum.infrastructure.dao.GenericDao;
import br.com.scrum.infrastructure.repository.SprintDao;

public class SprintDaoImpl implements SprintDao {
	
	private Dao<Sprint, Integer> sprintDao = new GenericDao<Sprint, Integer>(Sprint.class);

	@Override
	public Sprint salva (Sprint sprint) {
		return ( sprint.getId() != 0 ? sprintDao.merge(sprint) : sprintDao.persist(sprint) );
	}

	@Override
	public void remove (Sprint sprint) {
		sprintDao.remove(sprint);
	}

	@Override
	public Sprint comId (int id) {
		return sprintDao.find(id);
	}

	@Override
	public List<Sprint> todosSprints() {
		return sprintDao.list();
	}

	@Override
	public void fecha () {
		sprintDao.close();
	}

}
