package br.com.scrum.domain.repository.sprint;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import br.com.scrum.domain.entity.Projeto;
import br.com.scrum.domain.entity.Sprint;
import br.com.scrum.domain.enums.Const;
import br.com.scrum.infrastructure.decorator.ProjetoDaoImpl;
import br.com.scrum.infrastructure.decorator.SprintDaoImpl;
import br.com.scrum.infrastructure.repository.ProjetoDao;
import br.com.scrum.infrastructure.repository.SprintDao;

public class TestSprintRepository {

	private static SprintDao sprintDao;
	private static ProjetoDao projetoDao;
	private static Integer projetoID;
	private static Integer sprintID;

	@BeforeClass
	public static void before() throws Exception {
		after();
		sprintDao = new SprintDaoImpl();
		projetoDao = new ProjetoDaoImpl();
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
		Projeto p = projetoDao.comId(2);	
		Sprint s = new Sprint();		
		s.setNome("Sprint_03");
		s.setDataInicio(new Date());
		s.setDataFim(new Date());
		s.setProjeto(p);	
		
		sprintDao.salva(s);
		
		projetoID = s.getProjeto().getId();
		sprintID = s.getId();

		assertNotNull(projetoID);
		assertNotNull(sprintID);		
	}

//	private void remove() {		
//		Sprint s = sprintDao.comId(sprintID);
//		sprintDao.remove(s);
//		
//		Projeto p = projetoDao.comId(projetoID);
//		projetoDao.remove(p);
//
//	}

}
