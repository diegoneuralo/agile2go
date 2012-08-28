package br.com.scrum.infrastructure.factory;

import java.util.logging.Logger;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManagerFactory;

import org.jboss.solder.core.ExtensionManaged;

public class Resources {

	@ExtensionManaged
	@Produces
	@ConversationScoped
	EntityManagerFactory producerField;
   
   @Produces
   public Logger produceLog(InjectionPoint injectionPoint) {
      return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
   }
 
   
   
}
