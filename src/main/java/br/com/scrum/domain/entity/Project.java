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
@Table(name = "PROJECT", schema = Const.SCHEMA, uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Project implements Serializable {

	private static final long serialVersionUID = -2102744528226591109L;		

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROJECT_ID")
	private int id;

	@NotBlank(message = "name is a required field")
	@Column(name = "NAME", nullable = false, unique = true, length = 60)
	private String name;

	@NotBlank(message = "description is a required field")
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "LAST_DATE", nullable = false)
	private Date lastDate;

	@Column(name = "COMPANY", nullable = false, length = 60)
	private String company;

	public Project() { }

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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
		if (!(obj instanceof Project))
			return false;
		Project other = (Project) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
