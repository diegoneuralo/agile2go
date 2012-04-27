package br.com.scrum.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotBlank;

import br.com.scrum.domain.enums.Const;

@Entity
@Table(name = "project", schema = Const.SCHEMA, uniqueConstraints = {@UniqueConstraint(columnNames = "nome")})
public class Project implements Serializable {

	private static final long serialVersionUID = -2102744528226591109L;		

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROJETO_ID")
	private int id;

	@NotBlank(message = "type a name")
	@Column(name = "NOME", nullable = false, unique = true, length = 60)
	private String nome;

	@NotBlank(message = "type a description")
	@Column(name = "DESCRICAO", nullable = false)
	private String descricao;

	@Column(name = "DATA_ENTREGA", nullable = false)
	private Date dataEntrega;

	@Column(name = "EMPRESA", nullable = false, length = 60)
	private String empresa;
	
//	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	private List<Sprint> sprints;

	public Project() { }

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

	public final String getDescricao() {
		return descricao;
	}

	public final void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public final Date getDataEntrega() {
		return dataEntrega;
	}

	public final void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}		

	public final String getEmpresa() {
		return empresa;
	}

	public final void setEmpresa(String empresa) {
		this.empresa = empresa;
	}		
	
	public void addSprint(Sprint sprint) {
		this.addSprint(sprint);
	}
	
//	public final List<Sprint> getSprints() {
//		return sprints;
//	}
//
//	public final void setSprints(List<Sprint> sprints) {
//		this.sprints = sprints;
//	}

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
		if (!(obj instanceof Project))
			return false;
		Project other = (Project) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
