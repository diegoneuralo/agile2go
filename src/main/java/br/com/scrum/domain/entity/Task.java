package br.com.scrum.domain.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import br.com.scrum.domain.enums.Status;

@Entity
@Table(name = "TASK", schema = Const.SCHEMA)
@NamedQueries(
		@NamedQuery(name="Task.getLastId", query = "SELECT t FROM Task t WHERE t.id = (select MAX(t.id) FROM Task t)"))
public class Task implements Serializable {

	private static final long serialVersionUID = 3651157203865611931L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TASK_ID")
	private int id;

	@NotEmpty(message = "name is a required field") 
	@Column(name = "NAME", nullable = false, length = 60)
	private String name;
	
	@Column(name = "RESOURCE", nullable = false, length = 60)
	private int resource;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "STATUS", length = 15)
	private Status status;
	
	@Column(name = "LOCAL", nullable = false, length = 60)
	private String local;

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "ITEM_ID", referencedColumnName = "ITEM_ID")
	private Item item;		

	public Task() { }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getResource() {
		return resource;
	}

	public void setResource(int resource) {
		this.resource = resource;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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
		if (!(obj instanceof Task))
			return false;
		Task other = (Task) obj;
		if (id != other.id)
			return false;
		return true;
	}	

}
