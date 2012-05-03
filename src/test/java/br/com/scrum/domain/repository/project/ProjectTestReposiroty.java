package br.com.scrum.domain.repository.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.AfterClass;
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

public class ProjectTestReposiroty {

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
	public void saveACompletProject() throws Exception {
		criaProjetoCompleto();
	}

	@Test
	public void BDD() throws Exception {
		assertNotNull(salva());		
		update();
		withId();
		findAll();
		remove();
	}

	private Project salva() throws Exception  {
		Project p = new Project();
		p.setName("Scrum2Go");
		p.setDescription("Um project com o objetivo de agilizar o processo de desenvolvimento de software");
		p.setLastDate(new Date());
		p.setCompany("Vitae");		
		p = projectDao.save(p);
		return p;
	}	

	private void update() throws Exception {
		Project p = projectDao.withId(2);		
		p.setName("Agile2Go");		
		p.setDescription("A project for agile teams");
		p = projectDao.save(p);		
		assertEquals(2, p.getId());
	}

	private void withId() {
		Project p = projectDao.withId(2);		
		assertNotNull(p);
		assertSame(2, p.getId());		
	}

	private void findAll() throws Exception {		
		List<Project> projects = projectDao.findAll();
		assertTrue(!projects.isEmpty() && projects != null);
		assertEquals(1, projects.size());		
	}

	private void remove() {
		Project p = projectDao.withId(2);		
		projectDao.remove(p);
		assertNull(projectDao.withId(2));
	}

	private void criaProjetoCompleto() throws Exception {
		Project p = new Project();		
		p.setName("Agile2Go");
		p.setDescription("A software for agile teams");
		p.setCompany("São José dos Campos");
		p.setLastDate(new Date());
		projectDao.save(p);

		Sprint s = new Sprint();		
		s.setName("Sprint_01 ");
		s.setStartDate(new Date());
		s.setEndDate(new Date());
		s.setProject(p);
		sprintDao.salva(s);

		Item i = new Item();		
		i.setDescription("Item_01");
		i.setPriority(5);
		i.setSprint(s);
		itemDao.salva(i);

		Task t = new Task();		
		t.setName("Task ");
		t.setResource(5);
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
