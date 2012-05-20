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

import br.com.scrum.domain.entity.Project;
import br.com.scrum.domain.entity.Sprint;
import br.com.scrum.domain.entity.Task;
import br.com.scrum.domain.entity.User;
import br.com.scrum.domain.entity.enums.Const;
import br.com.scrum.domain.entity.enums.Status;
import br.com.scrum.domain.repository.ProjectRepository;
import br.com.scrum.domain.repository.SprintRepository;
import br.com.scrum.domain.repository.TaskRepository;
import br.com.scrum.domain.repository.UserRepository;
import br.com.scrum.domain.service.ProjectService;
import br.com.scrum.domain.service.SprintService;
import br.com.scrum.domain.service.TaskService;
import br.com.scrum.domain.service.UserService;

public class ProjectTestRepository {
		
	private static UserRepository userRepository;
	private static ProjectRepository projectRepository;
	private static SprintRepository sprintRepository;	
	private static TaskRepository taskRepository;

	@BeforeClass
	public static void before() throws Exception {
		after();
		userRepository = new UserService();
		projectRepository = new ProjectService();
		sprintRepository = new SprintService();		
		taskRepository = new TaskService();
	}

	@AfterClass
	public static void after() throws Exception {
		EntityManager em = Persistence.createEntityManagerFactory(Const.SCHEMA).createEntityManager();
		em.close();
	}

	@Test
	public void saveACompletProject() throws Exception {
		criaProjetoCompleto();
	}

//	@Test
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
		p = projectRepository.save(p);
		return p;
	}	

	private void update() throws Exception {
		Project p = projectRepository.withId(2);		
		p.setName("Agile2Go");		
		p.setDescription("A project for agile teams");
		p = projectRepository.save(p);		
		assertEquals(2, p.getId());
	}

	private void withId() {
		Project p = projectRepository.withId(2);		
		assertNotNull(p);
		assertSame(2, p.getId());		
	}

	private void findAll() throws Exception {		
		List<Project> projects = projectRepository.findAll();
		assertTrue(!projects.isEmpty() && projects != null);
		assertEquals(1, projects.size());		
	}

	private void remove() throws Exception {
		Project p = projectRepository.withId(2);		
		projectRepository.remove(p);
		assertNull(projectRepository.withId(2));
	}

	private void criaProjetoCompleto() throws Exception {
		User u = userRepository.withId(1);
		
		Project p = new Project();		
		p.setName("Agile2Go");
		p.setDescription("A software for agile teams");
		p.setCompany("São José dos Campos");
		p.setLastDate(new Date());		
		projectRepository.save(p);

		Sprint s = new Sprint();		
		s.setName("Sprint_01 ");
		s.setStartDate(new Date());
		s.setEndDate(new Date());
		s.setProject(p);
		sprintRepository.save(s);		

		Task t = new Task();		
		t.setStory("Task ");		
		t.setStatus(Status.TODO);
//		t.setItem(i);		
		taskRepository.save(t);

		assertNotNull(p);
		assertNotNull(s);		
		assertNotNull(t);

		Project p2 = projectRepository.withId(p.getId());
		assertEquals(1, p2.getId());

		Sprint s2 = sprintRepository.withId(s.getId());
		assertEquals(1, s2.getId());

		Task t2 = taskRepository.withId(t.getId());
		assertEquals(1, t2.getId());
	}

}
