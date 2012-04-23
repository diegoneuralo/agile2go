package br.com.scrum.infrastructure.repository;

import java.util.List;

import br.com.scrum.domain.entity.Item;

public interface ItemDao {
	
	Item salva (Item item);
	
	void remove (Item item);
	
	Item comId (int id);
	
	List<Item> todosItens ();
	
	void fecha ();
}
