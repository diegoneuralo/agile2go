package br.com.scrum.infrastructure.factory;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;

import br.com.scrum.infrastructure.dao.GenericRepository;

@SuppressWarnings("rawtypes")
public class RepositoryFactory {

	@Produces
	@SuppressWarnings("unchecked")
	public GenericRepository createGenericRepository (InjectionPoint injection, EntityManager em) {
		ParameterizedType type = (ParameterizedType) injection.getType();
		Class clazz = (Class) type.getActualTypeArguments()[0];
		return new GenericRepository(clazz, em);
	}

}
