package br.com.scrum.infrastructure.decorator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.NoResultException;
import javax.validation.ReportAsSingleViolation;

import br.com.scrum.domain.entity.Usuario;
import br.com.scrum.infrastructure.dao.Entity;
import br.com.scrum.infrastructure.dao.GenericRepository;
import br.com.scrum.infrastructure.dao.exception.BusinessException;
import br.com.scrum.infrastructure.repository.UsuarioDao;

public class UsuarioDaoImpl implements UsuarioDao, Serializable {

	private static final long serialVersionUID = 7103375420674460345L;

	private Entity<Usuario, Integer> dao = new GenericRepository<Usuario, Integer>(Usuario.class);

	public Usuario salva (Usuario usuario) throws Exception {
		try {
			return ( usuario.getId() != 0 ? dao.merge(usuario) : dao.persist(usuario) );
		} catch ( Exception e ) {
			throw e;
		}		
	}

	public void remove (Usuario usuario) throws Exception {
		try {
			usuario = dao.find(usuario.getId());
			dao.remove(usuario);			
		} catch ( Exception e ) {
			throw e;
		}
	}

	public Usuario comId (int id) {
		return dao.find(id);
	}

	public Usuario comLogin (final String login, final String senha) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Usuario.LOGIN, login);
		params.put(Usuario.SENHA, senha);		
		try {
			return dao.getByNamedQuery("Usuario.getByLogin", params);
		} catch ( Exception e ) {
			throw e;
		}
	}

	public List<Usuario> listatodos () throws Exception {
		try {
			return dao.list();
		} catch ( Exception e ) {
			throw e;
		}		
	}

}
