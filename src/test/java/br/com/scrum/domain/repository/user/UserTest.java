package br.com.scrum.domain.repository.user;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.scrum.domain.entity.User;
import br.com.scrum.domain.entity.enums.UserRole;
import br.com.scrum.domain.service.AuthenticationService;
import br.com.scrum.infrastructure.factory.EntityManagerFactoryProducer;

public class UserTest {

	private static EntityManager em;	
	private static AuthenticationService service;
	private static Integer ID; 

	@BeforeClass					
	public static void before() throws Exception {
		em = new EntityManagerFactoryProducer().getEntityManager();
		service = new AuthenticationService().setEm(em);		
	}

	@AfterClass
	public static void after() throws Exception {				
		em.close();
	}

	@Test
	public void success() throws Exception {		
		Integer random = new Random().nextInt();
		save(random);
		update(random);
		newUser(random);
		userWithLogin();
		findAll();					
	}		

	private User save(long random) throws Exception {
		final User u = new User();		
		u.setName("Sophia" + random);
		u.setLogin(String.valueOf(random));
		u.setPassword("123");
		u.setRole(UserRole.MASTER);
		service.save(u);
		assertNotNull(u.getId());
		ID = u.getId();
		return u;
	}

	private void update(long random) throws Exception {
		User u = service.withId(ID);
		u.setName("Rafael" + random);
		service.update(u);
		assertSame(u.getId(), ID);
	}

	private void newUser(long random) throws Exception {
		final User u = new User();
		u.setName("Juliana" + random);
		u.setLogin(String.valueOf(random + random));
		u.setPassword("123");
		u.setRole(UserRole.TEAM);
		service.save(u);
		assertNotNull(u);			
	}
	
	private void userWithLogin() throws Exception {
		User u = service.withId(ID);
		User u2 = service.withLogin(u.getLogin(), u.getPassword());
		assertNotNull(u2.getLogin(), u2.getPassword());
	}
	
	private void findAll() throws Exception {
		List<User> users = service.findAll();
		assertTrue(!users.isEmpty());
	}

}
