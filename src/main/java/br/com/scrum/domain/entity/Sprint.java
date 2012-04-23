package br.com.scrum.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;

import br.com.scrum.domain.enums.Const;

@Entity
@Table(name = "SPRINT", schema = Const.SCHEMA)
@NamedQueries(
		@NamedQuery(name="Sprint.getLastId", query="SELECT s FROM Sprint as s where s.id = (select MAX(s.id) FROM Sprint s)"))
public class Sprint implements Serializable {

	private static final long serialVersionUID = 4897729582058383675L;
	
	public static final String ID = "id";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SPRINT_ID")
	private int id;
		
	@NotBlank(message = "nome do sprint é obrigatório!")
	@Column(name = "NOME", nullable = false, length = 60)
	private String nome;
		
	@Column(name = "DATA_INICIO", nullable = false)
	private Date dataInicio;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_FIM", nullable = false)
	private Date dataFim;
		
	@ManyToOne	
	@JoinColumn(name = "PROJETO_ID", referencedColumnName = "PROJETO_ID")	
	private Projeto projeto;	
		
	public Sprint() { }		

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

	public final Date getDataInicio() {
		return dataInicio;
	}

	public final void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public final Date getDataFim() {
		return dataFim;
	}

	public final void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	public final Projeto getProjeto() {
		return projeto;
	}

	public final void setProjeto(Projeto projeto) {		
		this.projeto = projeto;
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
		if (!(obj instanceof Sprint))
			return false;
		Sprint other = (Sprint) obj;
		if (id != other.id)
			return false;
		return true;
	}


}

