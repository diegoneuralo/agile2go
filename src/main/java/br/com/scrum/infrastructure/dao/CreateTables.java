package br.com.scrum.infrastructure.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.scrum.domain.entity.enums.Const;

public class CreateTables {	

	@Inject
	private static EntityManager em;
	
	public static void main(String[] args) {
		em = Persistence.createEntityManagerFactory(Const.SCHEMA).createEntityManager();
		em.close();				
	}
			
}
