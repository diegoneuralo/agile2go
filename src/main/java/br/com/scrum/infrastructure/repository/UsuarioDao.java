package br.com.scrum.infrastructure.repository;

import java.io.Serializable;
import java.util.List;

import br.com.scrum.domain.entity.Usuario;

public interface UsuarioDao{
	
	Usuario salva (Usuario u) throws Exception;
	
	void remove (Usuario u) throws Exception;
    
	Usuario comId (int id);
    
	Usuario comLogin (String usuario, String senha) throws Exception;
    
	List<Usuario> listatodos () throws Exception;

}
