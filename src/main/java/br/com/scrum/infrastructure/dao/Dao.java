package br.com.scrum.infrastructure.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface Dao <T, IDTipo extends Serializable> extends Serializable {
	
	T merge (T obj);
	
	T persist (T obj);
	
	void remove (T obj);
	
	T find (IDTipo obj);
	
	List<T> list ();
	
	List<T> listByNamedQuery (String queryName, Map<String, Object> params);
	
	T getByNamedQuery (String queryName, Map<String, Object> params);
	
	void close ();
	
}
