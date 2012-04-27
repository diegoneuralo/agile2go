package br.com.scrum.domain.repository.usuario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.scrum.domain.entity.User;
import br.com.scrum.domain.enums.Const;
import br.com.scrum.domain.enums.UserRole;
import br.com.scrum.infrastructure.dao.JPAUtil;
import br.com.scrum.infrastructure.decorator.UserDaoImpl;
import br.com.scrum.infrastructure.repository.UserDao;

//@RunWith(Arquillian.class)
public class UsuarioTestRepository {
	
//	@Deployment
//	public static Archive<?> createDeployment () {
//		return ShrinkWrap.create(JavaArchive.class, "test.jar")
//				.addClass(UserDao.class)
//				.addAsManifestResource("test-persistence.xml", ArchivePaths.create("META-INF/persistence.xml"));
//	}
		
	private static EntityManager em;	
	@Inject private static UserDao userDao;

	@BeforeClass					
	public static void before() throws Exception {
		try {
			em = new JPAUtil().getEntityManager();
			userDao = new UserDaoImpl();			
		} catch (Exception e) {
			throw new Exception("ERRO: ", e.getCause());
		}
	}

	@AfterClass
	public static void after() throws Exception {				
		em.close();
	}

	@Test
	public void success() throws Exception {
		try {
			assertNotNull(salvaNovoUsuario());
//			atualiza();
//			novoUsuario();
//			usuarioComLogin();
//			listaTodos();			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}		
	
	private User salvaNovoUsuario() throws Exception {
		final User u = new User();		
		u.setNome("Juliana");
		u.setLogin("adm");
		u.setSenha("123");
		u.setRole(UserRole.TEAM);
		userDao.save(u);
		assertNotNull(u.getId());		
		return u;
	}

	private void atualiza() throws Exception { 
		final User u = userDao.withId(1);
		u.setLogin("adm");
		u.setRole(UserRole.MASTER);
		userDao.save(u);
		assertSame(u.getId(), 1);
	}

	private void novoUsuario() throws Exception {
		final User u = new User();
		u.setNome("Barbara");
		u.setLogin("team");
		u.setSenha("123");
		u.setRole(UserRole.MASTER);
		userDao.save(u);
		assertNotNull(u);			
	}

	private void usuarioComLogin() throws Exception {
		try {
			final User u = userDao.withLogin("adm", "123");
			assertEquals("adm", u.getLogin());
			assertEquals("123", u.getSenha());
		} catch (Exception e) {
			e.getCause().getLocalizedMessage();
		}
		
//		assertNotNull(u);
//		assertEquals(1, u.getId());		
	}

	private void listaTodos() throws Exception {
		List<User> users = userDao.findAll();
		assertNotNull(users);
		assertEquals(2, users.size());				
	}
	
	private void remove() throws Exception {
		User u = userDao.withId(2);		
		userDao.remove(u);
		assertNull(u);
	}


}
