package br.com.scrum.domain.repository.sprint;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import br.com.scrum.domain.entity.Project;
import br.com.scrum.domain.entity.Sprint;
import br.com.scrum.domain.enums.Const;
import br.com.scrum.infrastructure.decorator.ProjectDaoImpl;
import br.com.scrum.infrastructure.decorator.SprintDaoImpl;
import br.com.scrum.infrastructure.repository.ProjectDao;
import br.com.scrum.infrastructure.repository.SprintDao;

public class SprintTestRepository {

	private static SprintDao sprintDao;
	private static ProjectDao projectDao;
	private static Integer projetoID;
	private static Integer sprintID;

	@BeforeClass
	public static void before() throws Exception {
		after();
		sprintDao = new SprintDaoImpl();
		projectDao = new ProjectDaoImpl();
	}

	@AfterClass
	public static void after() throws Exception {
		EntityManager em = Persistence.createEntityManagerFactory(Const.SCHEMA).createEntityManager();
		em.close();
	}

	@Test
	public void salvaSprintCompleto() throws Exception {
		criaSprint();
//		remove();
	}

	private void criaSprint() {		
		Project p = projectDao.withId(2);	
		Sprint s = new Sprint();		
		s.setName("Sprint_03");
		s.setStartDate(new Date());
		s.setEndDate(new Date());
		s.setProject(p);	
		
		sprintDao.salva(s);
		
		projetoID = s.getProject().getId();
		sprintID = s.getId();

		assertNotNull(projetoID);
		assertNotNull(sprintID);		
	}

//	private void remove() {		
//		Sprint s = sprintDao.comId(sprintID);
//		sprintDao.remove(s);
//		
//		Project p = projectDao.comId(projetoID);
//		projectDao.remove(p);
//
//	}

}
