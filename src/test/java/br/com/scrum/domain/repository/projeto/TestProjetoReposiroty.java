package br.com.scrum.domain.repository.projeto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.scrum.domain.entity.Item;
import br.com.scrum.domain.entity.Projeto;
import br.com.scrum.domain.entity.Sprint;
import br.com.scrum.domain.entity.Tarefa;
import br.com.scrum.domain.enums.Const;
import br.com.scrum.domain.enums.Status;
import br.com.scrum.infrastructure.decorator.ItemDaoImpl;
import br.com.scrum.infrastructure.decorator.ProjetoDaoImpl;
import br.com.scrum.infrastructure.decorator.SprintDaoImpl;
import br.com.scrum.infrastructure.decorator.TarefaDaoImpl;
import br.com.scrum.infrastructure.repository.ItemDao;
import br.com.scrum.infrastructure.repository.ProjetoDao;
import br.com.scrum.infrastructure.repository.SprintDao;
import br.com.scrum.infrastructure.repository.TarefaDao;
import br.com.scrum.view.util.Util;

public class TestProjetoReposiroty {

	private static ProjetoDao projetoDao;
	private static SprintDao sprintDao;
	private static ItemDao itemDao;
	private static TarefaDao tarefaDao;

	@BeforeClass
	public static void before() throws Exception {
		after();
		projetoDao = new ProjetoDaoImpl();
		sprintDao = new SprintDaoImpl();
		itemDao = new ItemDaoImpl();
		tarefaDao = new TarefaDaoImpl();
	}

	@AfterClass
	public static void after() throws Exception {
		EntityManager em = Persistence.createEntityManagerFactory(Const.SCHEMA).createEntityManager();
		em.close();
	}

//	@Test
	public void salvaProjetoCompleto() throws Exception {
		criaProjetoCompleto();
	}

			@Test
	public void BDD() throws Exception {
		assertNotNull(salva());		
		atualiza();
		comId();
		listaTodos();
		remove();
	}

	private Projeto salva() throws Exception  {
		Projeto p = new Projeto();
		p.setNome("Scrum2Go");
		p.setDescricao("Um projeto com o objetivo de agilizar o processo de desenvolvimento de software");
		p.setDataEntrega(new Date());
		p.setEmpresa("Vitae");		
		p = projetoDao.salva(p);
		return p;
	}	

	private void atualiza() throws Exception {
		Projeto p = projetoDao.comId(1);		
		p.setNome("Agile2Go");		
		p = projetoDao.salva(p);		
		assertEquals(1, p.getId());
	}

	private void comId() {
		Projeto p = projetoDao.comId(1);		
		assertNotNull(p);
		assertSame(1, p.getId());		
	}

	private void listaTodos() throws Exception {		
		List<Projeto> projetos = projetoDao.listaTodos();
		assertTrue(!projetos.isEmpty() && projetos != null);
		assertEquals(1, projetos.size());		
	}

	private void remove() {
		Projeto p = projetoDao.comId(1);		
		projetoDao.remove(p);
		assertNull(projetoDao.comId(1));
	}

	private void criaProjetoCompleto() throws Exception {
		Projeto p = new Projeto();		
		p.setNome("Agile2Go");
		p.setDescricao("Um software para equipes ágeis");
		p.setEmpresa("São José dos Campos");
		p.setDataEntrega(new Date());
		projetoDao.salva(p);

		Sprint s = new Sprint();		
		s.setNome("Sprint_01 ");
		s.setDataInicio(new Date());
		s.setDataFim(new Date());
		s.setProjeto(p);
		sprintDao.salva(s);

		Item i = new Item();		
		i.setDescricao("Item_01");
		i.setPrioridade(5);
		i.setSprint(s);
		itemDao.salva(i);

		Tarefa t = new Tarefa();		
		t.setNome("Tarefa ");
		t.setRecurso(5);
		t.setLocal("São José dos Campos");
		t.setStatus(Status.TODO);
		t.setItem(i);		
		tarefaDao.salva(t);

		assertNotNull(p);
		assertNotNull(s);
		assertNotNull(i);
		assertNotNull(t);

		Projeto p2 = projetoDao.comId(p.getId());
		assertEquals(1, p2.getId());

		Sprint s2 = sprintDao.comId(s.getId());
		assertEquals(1, s2.getId());

		Item i2 = itemDao.comId(i.getId());
		assertEquals(1, i2.getId());

		Tarefa t2 = tarefaDao.comId(t.getId());
		assertEquals(1, t2.getId());
	}

}
