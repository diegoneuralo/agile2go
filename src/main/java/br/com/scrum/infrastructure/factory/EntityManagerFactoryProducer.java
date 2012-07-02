package br.com.scrum.infrastructure.factory;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.jboss.solder.core.ExtensionManaged;

import br.com.scrum.domain.entity.enums.Const;

public class EntityManagerFactoryProducer {
	
	@ExtensionManaged
	@ConversationScoped
	@Produces
	@PersistenceUnit(unitName=Const.SCHEMA)
	EntityManagerFactory producerField;

//	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(Const.SCHEMA);
//
//	@Produces 
//	@RequestScoped
//	@ExtensionManaged
//	@PersistenceContext
//	public EntityManager getEntityManager() {
//		return factory.createEntityManager();
//	}

//	public void close(@Disposes EntityManager em) {
//		em.close();
//	}

}
