package br.com.scrum.infrastructure.factory;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.scrum.domain.entity.enums.Const;

public class JPAUtil {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(Const.SCHEMA);
	
	@Produces 
	@RequestScoped
	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

	public void close(@Disposes EntityManager em) {
		em.close();
	}

}
