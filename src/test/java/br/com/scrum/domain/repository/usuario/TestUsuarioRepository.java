package br.com.scrum.domain.repository.usuario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.scrum.domain.entity.Usuario;
import br.com.scrum.domain.enums.Const;
import br.com.scrum.domain.enums.UsuarioRole;
import br.com.scrum.infrastructure.dao.JPAUtil;
import br.com.scrum.infrastructure.decorator.UsuarioDaoImpl;
import br.com.scrum.infrastructure.repository.UsuarioDao;

public class TestUsuarioRepository {
		
	private static EntityManager em;	
	private static UsuarioDao usuarioDao;

	@BeforeClass					
	public static void before() throws Exception {
		try {
			em = new JPAUtil().getEntityManager();
			usuarioDao = new UsuarioDaoImpl();			
		} catch (Exception e) {
			throw new Exception("ERRO: ", e.getCause());
		}
	}

	@AfterClass
	public static void after() throws Exception {				
		em.close();
	}

	@Test
	public void success() throws Exception {
		try {
			assertNotNull(salvaNovoUsuario());
//			atualiza();
//			novoUsuario();
//			usuarioComLogin();
//			listaTodos();			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}		
	
	private Usuario salvaNovoUsuario() throws Exception {
		final Usuario u = new Usuario();		
		u.setNome("Juliana");
		u.setLogin("adm");
		u.setSenha("123");
		u.setRole(UsuarioRole.TEAM);
		usuarioDao.salva(u);
		assertNotNull(u.getId());		
		return u;
	}

	private void atualiza() throws Exception { 
		final Usuario u = usuarioDao.comId(1);
		u.setLogin("adm");
		u.setRole(UsuarioRole.MASTER);
		usuarioDao.salva(u);
		assertSame(u.getId(), 1);
	}

	private void novoUsuario() throws Exception {
		final Usuario u = new Usuario();
		u.setNome("Barbara");
		u.setLogin("team");
		u.setSenha("123");
		u.setRole(UsuarioRole.MASTER);
		usuarioDao.salva(u);
		assertNotNull(u);			
	}

	private void usuarioComLogin() throws Exception {
		try {
			final Usuario u = usuarioDao.comLogin("adm", "123");
			assertEquals("adm", u.getLogin());
			assertEquals("123", u.getSenha());
		} catch (Exception e) {
			e.getCause().getLocalizedMessage();
		}
		
//		assertNotNull(u);
//		assertEquals(1, u.getId());		
	}

	private void listaTodos() throws Exception {
		List<Usuario> users = usuarioDao.listatodos();
		assertNotNull(users);
		assertEquals(2, users.size());				
	}
	
	private void remove() throws Exception {
		Usuario u = usuarioDao.comId(2);		
		usuarioDao.remove(u);
		assertNull(u);
	}


}
