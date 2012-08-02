package br.com.scrum.infrastructure.factory;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Default;
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
	@Default
	EntityManagerFactory producerField;
}
