package br.com.scrum.infrastructure.decorator;

import java.util.List;

import br.com.scrum.domain.entity.Item;
import br.com.scrum.infrastructure.dao.Dao;
import br.com.scrum.infrastructure.dao.GenericDao;
import br.com.scrum.infrastructure.repository.ItemDao;

public class ItemDaoImpl implements ItemDao {
	
	private Dao<Item, Integer> itemDao = new GenericDao<Item, Integer>(Item.class);

	@Override
	public Item salva (Item item) {
		return ( item.getId() != 0 ? itemDao.merge(item) : itemDao.persist(item) );
	}

	@Override
	public void remove (Item item) {
		itemDao.remove(item);
	}

	@Override
	public Item comId (int id) {
		return itemDao.find(id);
	}

	@Override
	public List<Item> todosItens() {
		return itemDao.list();
	}

	@Override
	public void fecha () {
		itemDao.close();
		
	}
	
}