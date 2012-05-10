package br.com.scrum.domain.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.scrum.domain.entity.enums.Const;
import br.com.scrum.domain.entity.enums.Status;

@Entity
@Table(name = "TASK", schema = Const.SCHEMA, uniqueConstraints =
					  @UniqueConstraint(columnNames = {"NAME"}))
@NamedQueries(
		@NamedQuery(name="Task.getLastId", query = "SELECT t FROM Task t WHERE t.id = (select MAX(t.id) FROM Task t)"))
public class Task implements Serializable {	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TASK_ID")
	private int id;

	@NotEmpty(message = "name is a required field") 
	@Column(name = "NAME", nullable = false, length = 60)
	private String name;
	
	@NotEmpty(message = "prioriry is a required field")
	@Column(name = "PRIORITY", nullable = false, length = 60)
	private int priority;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "START_DATE")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE")
    private Date endDate;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "STATUS", length = 15)
	private Status status;
		
	@ManyToOne
	@JoinColumn(name = "SPRINT_ID", referencedColumnName = "SPRINT_ID")
	private Sprint sprint;		

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
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}		
	
	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
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
		if (!(obj instanceof Task))
			return false;
		Task other = (Task) obj;
		if (id != other.id)
			return false;
		return true;
	}	
	
	private static final long serialVersionUID = 3651157203865611931L;

}
