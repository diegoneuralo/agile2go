package br.com.scrum.infrastructure.decorator;

import java.util.List;

import javax.annotation.PostConstruct;

import br.com.scrum.domain.entity.Projeto;
import br.com.scrum.infrastructure.dao.Entity;
import br.com.scrum.infrastructure.dao.GenericRepository;
import br.com.scrum.infrastructure.repository.ProjetoDao;

public class ProjetoDaoImpl implements ProjetoDao {
	
	private Entity<Projeto, Integer> projetoDao = new GenericRepository<Projeto, Integer>(Projeto.class);
	
	public ProjetoDaoImpl projeto () {
		projetoDao = new GenericRepository<Projeto, Integer>(Projeto.class);
		return this;
	}
	
	@PostConstruct
	public void init () {
		projetoDao = new GenericRepository<Projeto, Integer>(Projeto.class);		
	}
	
	@Override
	public Projeto salva (Projeto projeto) throws Exception {
		try {
			return (projeto.getId() != 0  ? projetoDao.merge(projeto) : projetoDao.persist(projeto));			
		} catch ( Exception e ) {
			throw e;
		}
	}

	@Override
	public void remove (Projeto projeto) {
		projetoDao.remove(projeto);
	}

	@Override
	public Projeto comId (int id) {
		return projetoDao.find(id);
	}
	
	@Override
	public List<Projeto> listaTodos() {
		return projetoDao.list();
	}
	
	@Override
	public void fecha () {
		projetoDao.close();
	}

}
