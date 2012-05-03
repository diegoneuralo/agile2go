package br.com.scrum.infrastructure.dao;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.scrum.domain.enums.Const;
import javax.inject.Named;

public class JPAUtil {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(Const.SCHEMA);

	@Named
	@Produces 
	@RequestScoped
	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

	public void close(@Disposes EntityManager em) {
		em.close();
	}

}
