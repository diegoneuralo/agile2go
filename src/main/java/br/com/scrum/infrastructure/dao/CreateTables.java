package br.com.scrum.infrastructure.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import br.com.scrum.domain.enums.Const;

public class CreateTables {
	private static final String PERSISTENCE_UNIT = Const.SCHEMA;
	@PersistenceContext private static EntityManager em;
	private static EntityManagerFactory emf;

	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		em = emf.createEntityManager();
		em.close();
		emf.close();
	}
}
