package br.com.scrum.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.scrum.domain.enums.Const;
import br.com.scrum.domain.enums.UsuarioRole;

@Entity
@Table(name = "USUARIO", schema = Const.SCHEMA, uniqueConstraints = {@UniqueConstraint(columnNames = "NOME")})
@NamedQueries({
	@NamedQuery(name = "Usuario.getByLogin", query = "SELECT u FROM Usuario as u WHERE u.login = :login and u.senha = :senha")})
public class Usuario implements Serializable {

	private static final long serialVersionUID = -2854296962122780992L;
	
	public static final String LOGIN = "login";
	public static final String SENHA = "senha";

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "nome é campo obrigatório")
	@Size(min = 3, max = 25)
//	@Pattern(regexp = "[A-Z]", message = "usuário deve conter somente letras")
	@Column(name = "NOME", nullable = false, length = 100)
	private String nome;
	
	@NotEmpty(message = "login é campo obrigatório!")
	@Column(name = "LOGIN", nullable = false, unique = true, length = 20)
	private String login;
	
	@NotEmpty(message = "senha é campo obrigatório!")
	@Column(name = "SENHA", nullable = false, length = 15)
	private String senha;

	@Embedded
	private Endereco endereco;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private UsuarioRole role;	

	public Usuario() { }

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getNome() {
		return nome;
	}

	public final void setNome(String nome) {
		this.nome = nome;
	}	

	public final String getLogin() {
		return login;
	}

	public final void setLogin(String login) {
		this.login = login;
	}

	public final String getSenha() {
		return senha;
	}

	public final void setSenha(String senha) {
		this.senha = senha;
	}

	public final Endereco getEndereco() {
		return endereco;
	}

	public final void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public final UsuarioRole getRole() {
		return role;
	}

	public final void setRole(UsuarioRole role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Usuario))
			return false;
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}	

}
