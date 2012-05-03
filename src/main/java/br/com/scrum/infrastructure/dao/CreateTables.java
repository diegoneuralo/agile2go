//package br.com.scrum.infrastructure.dao;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Persistence;
//import javax.persistence.PersistenceContext;
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.ArchivePaths;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import br.com.scrum.domain.enums.Const;
//
//@RunWith(Arquillian.class)
//public class CreateTables {	
//
//	@PersistenceContext
//	private static EntityManager em;	
//
//	@Deployment
//	public static JavaArchive createTestArchive() {
//		return ShrinkWrap.create(JavaArchive.class, "test.jar")
//				.addPackages(true, "br.com.scrum.domain.repository.project.ProjectTestRepository")
//				.addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
//				.addAsManifestResource("test-persistence.xml", "persistence.xml");
//	}
//	
//	@Test
//	public void createTables () {		
//		em = Persistence.createEntityManagerFactory(Const.SCHEMA).createEntityManager();
//		em.close();		
//	}
//}
