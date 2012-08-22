package br.com.scrum.infrastructure.factory;

import java.util.logging.Logger;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.jboss.solder.core.ExtensionManaged;

import br.com.scrum.domain.entity.enums.Const;

public class Resources {

	@ExtensionManaged
	@Produces
	@ConversationScoped
	@PersistenceUnit(name = Const.SCHEMA)
	EntityManagerFactory producerField;
   
   @Produces
   public Logger produceLog(InjectionPoint injectionPoint) {
      return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
   }
 
   
   
}
