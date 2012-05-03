package br.com.scrum.domain.repository.user;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.scrum.domain.entity.User;
import br.com.scrum.domain.enums.Const;
import br.com.scrum.domain.enums.UserRole;
import br.com.scrum.infrastructure.decorator.UserDaoImpl;
import br.com.scrum.infrastructure.repository.UserDao;

public class UserTestRepository {

	private static EntityManager em;	
	private static UserDao userDao;

	@BeforeClass					
	public static void before() throws Exception {
		userDao = new UserDaoImpl();
		after();
	}

	@AfterClass
	public static void after() throws Exception {				
		em = Persistence.createEntityManagerFactory(Const.SCHEMA).createEntityManager();             
		em.close();
	}

	@Test
	public void success() throws Exception {		
//		save();
		update();
		//			novoUsuario();
		//			usuarioComLogin();
		//			listaTodos();					
	}		

	private User save() throws Exception {
		final User u = new User();		
		u.setName("Rafael Jesus");
		u.setLogin("adm");
		u.setPassword("123");
		u.setRole(UserRole.TEAM);
		userDao.save(u);
		assertNotNull(u.getId());		
		return u;
	}
	
	private void update() throws Exception {
		User u = userDao.withId(1);
		u.setName("Rafael Jesus");
		userDao.save(u);
		assertNotSame(u.getName(), "test");
	}




	//	@Deployment
	//	public static Archive<?> createDeployment () {
	//		return ShrinkWrap.create(WebArchive.class, "test.war")
	//				.addClasses(User.class, Address.class, UserRole.class)				
	//				.addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
	//				.addAsManifestResource("META-INF/persistence.xml", ArchivePaths.create("META-INF/persistence.xml"));
	//	}

	//	@PersistenceContext
	//	EntityManager em;
	//	
	//	@Inject
	//	UserTransaction utx;	

	//	@Before					
	//	public void before() throws Exception {	
	//		Context ctx = new InitialContext();
	//		utx = (UserTransaction) ctx.lookup("java:jboss/datasources/postgresDS");
	//		saveUser();
	//		update();
	//		beginTransaction();				
	//	}	
	//
	//	@After
	//	public void after() throws Exception {
	//		commitTransaction();
	//	}
	//	
	//	@Test
	//	public void saveUser() throws Exception {
	//		utx.begin();
	//		em.joinTransaction();	
	//		final User u = new User();		
	//		u.setName("Rafael");
	//		u.setLogin("adm");
	//		u.setPassword("adm");
	//		u.setRole(UserRole.TEAM);
	//		em.persist(u);
	//		utx.commit();
	//		assertNotNull(u.getId());				
	//	}

	//	@Test
	//	public void update() throws Exception { 
	//		utx.begin();
	//		em.joinTransaction();				
	//		final User u = em.find(User.class, 1);
	//		u.setLogin("adm");
	//		u.setRole(UserRole.MASTER);
	//		em.merge(u);
	//		utx.commit();
	//		assertSame(u.getId(), 1);
	//	}
	//	
	//	private void beginTransaction() throws NotSupportedException, SystemException {		
	//		utx.begin();
	//		em.joinTransaction();
	//	}
	//	
	//	private void commitTransaction() throws SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
	//		utx.commit();		
	//	}

	//	private void novoUsuario() throws Exception {
	//		final User u = new User();
	//		u.setName("Barbara");
	//		u.setLogin("team");
	//		u.setPassword("123");
	//		u.setRole(UserRole.MASTER);
	//		userDao.save(u);
	//		assertNotNull(u);			
	//	}
	//
	//	private void usuarioComLogin() throws Exception {
	//		try {
	//			final User u = userDao.withLogin("adm", "123");
	//			assertEquals("adm", u.getLogin());
	//			assertEquals("123", u.getPassword());
	//		} catch (Exception e) {
	//			e.getCause().getLocalizedMessage();
	//		}
	//
	//		//		assertNotNull(u);
	//		//		assertEquals(1, u.getId());		
	//	}
	//
	//	private void listaTodos() throws Exception {
	//		List<User> users = userDao.findAll();
	//		assertNotNull(users);
	//		assertEquals(2, users.size());				
	//	}
	//
	//	private void remove() throws Exception {
	//		User u = userDao.withId(2);		
	//		userDao.remove(u);
	//		assertNull(u);
	//	}


}
