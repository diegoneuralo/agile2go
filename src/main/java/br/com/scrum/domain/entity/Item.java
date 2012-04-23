package br.com.scrum.domain.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
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

import org.hibernate.validator.constraints.NotEmpty;

import br.com.scrum.domain.enums.Const;

@Entity
@Table(name = "ITEM", schema = Const.SCHEMA)
@NamedQueries(
		@NamedQuery(name = "Item.getLastId", query = "SELECT i FROM Item i WHERE i.id = (select MAX(i.id) FROM Item i)"))
public class Item implements Serializable {

	private static final long serialVersionUID = 2771999333254534016L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITEM_ID")
	private int id;

	@NotEmpty(message = "descrição é campo obrigatório!")
	@Column(name = "DESCRICAO", nullable = false)
	private String descricao;

	@Column(name = "PRIORIDADE", nullable = false, length = 1)
	private int prioridade;

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "SPRINT_ID", referencedColumnName = "SPRINT_ID")
	private Sprint sprint;			

	public Item() { }

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getDescricao() {
		return descricao;
	}

	public final void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public final int getPrioridade() {
		return prioridade;
	}

	public final void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public final Sprint getSprint() {
		return sprint;
	}

	public final void setSprint(Sprint sprint) {
		this.sprint = sprint;
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
		if (!(obj instanceof Item))
			return false;
		Item other = (Item) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
