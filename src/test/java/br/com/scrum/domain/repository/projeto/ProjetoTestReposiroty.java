package br.com.scrum.domain.repository.projeto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.scrum.domain.entity.Item;
import br.com.scrum.domain.entity.Project;
import br.com.scrum.domain.entity.Sprint;
import br.com.scrum.domain.entity.Task;
import br.com.scrum.domain.enums.Const;
import br.com.scrum.domain.enums.Status;
import br.com.scrum.infrastructure.decorator.ItemDaoImpl;
import br.com.scrum.infrastructure.decorator.ProjectDaoImpl;
import br.com.scrum.infrastructure.decorator.SprintDaoImpl;
import br.com.scrum.infrastructure.decorator.TaskDaoImpl;
import br.com.scrum.infrastructure.repository.ItemDao;
import br.com.scrum.infrastructure.repository.ProjectDao;
import br.com.scrum.infrastructure.repository.SprintDao;
import br.com.scrum.infrastructure.repository.TaskDao;
import br.com.scrum.view.util.Util;

public class ProjetoTestReposiroty {

	private static ProjectDao projectDao;
	private static SprintDao sprintDao;
	private static ItemDao itemDao;
	private static TaskDao taskDao;

	@BeforeClass
	public static void before() throws Exception {
		after();
		projectDao = new ProjectDaoImpl();
		sprintDao = new SprintDaoImpl();
		itemDao = new ItemDaoImpl();
		taskDao = new TaskDaoImpl();
	}

	@AfterClass
	public static void after() throws Exception {
		EntityManager em = Persistence.createEntityManagerFactory(Const.SCHEMA).createEntityManager();
		em.close();
	}

//	@Test
	public void salvaProjetoCompleto() throws Exception {
		criaProjetoCompleto();
	}

			@Test
	public void BDD() throws Exception {
		assertNotNull(salva());		
		atualiza();
		comId();
		listaTodos();
		remove();
	}

	private Project salva() throws Exception  {
		Project p = new Project();
		p.setNome("Scrum2Go");
		p.setDescricao("Um project com o objetivo de agilizar o processo de desenvolvimento de software");
		p.setDataEntrega(new Date());
		p.setEmpresa("Vitae");		
		p = projectDao.save(p);
		return p;
	}	

	private void atualiza() throws Exception {
		Project p = projectDao.withId(1);		
		p.setNome("Agile2Go");		
		p = projectDao.save(p);		
		assertEquals(1, p.getId());
	}

	private void comId() {
		Project p = projectDao.withId(1);		
		assertNotNull(p);
		assertSame(1, p.getId());		
	}

	private void listaTodos() throws Exception {		
		List<Project> projects = projectDao.findAll();
		assertTrue(!projects.isEmpty() && projects != null);
		assertEquals(1, projects.size());		
	}

	private void remove() {
		Project p = projectDao.withId(1);		
		projectDao.remove(p);
		assertNull(projectDao.withId(1));
	}

	private void criaProjetoCompleto() throws Exception {
		Project p = new Project();		
		p.setNome("Agile2Go");
		p.setDescricao("Um software para equipes ágeis");
		p.setEmpresa("São José dos Campos");
		p.setDataEntrega(new Date());
		projectDao.save(p);

		Sprint s = new Sprint();		
		s.setNome("Sprint_01 ");
		s.setDataInicio(new Date());
		s.setDataFim(new Date());
		s.setProjeto(p);
		sprintDao.salva(s);

		Item i = new Item();		
		i.setDescricao("Item_01");
		i.setPrioridade(5);
		i.setSprint(s);
		itemDao.salva(i);

		Task t = new Task();		
		t.setNome("Task ");
		t.setRecurso(5);
		t.setLocal("São José dos Campos");
		t.setStatus(Status.TODO);
		t.setItem(i);		
		taskDao.salva(t);

		assertNotNull(p);
		assertNotNull(s);
		assertNotNull(i);
		assertNotNull(t);

		Project p2 = projectDao.withId(p.getId());
		assertEquals(1, p2.getId());

		Sprint s2 = sprintDao.comId(s.getId());
		assertEquals(1, s2.getId());

		Item i2 = itemDao.comId(i.getId());
		assertEquals(1, i2.getId());

		Task t2 = taskDao.comId(t.getId());
		assertEquals(1, t2.getId());
	}

}
