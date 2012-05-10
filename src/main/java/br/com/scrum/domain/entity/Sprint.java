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
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotBlank;

import br.com.scrum.domain.entity.enums.Const;

@Entity
@Table(name = "SPRINT", schema = Const.SCHEMA, uniqueConstraints =
						@UniqueConstraint(columnNames = {"NAME", "PROJECT_ID"}))
@NamedQueries(
		@NamedQuery(name="Sprint.getLastId", query="SELECT s FROM Sprint as s where s.id = (select MAX(s.id) FROM Sprint s)"))
public class Sprint implements Serializable {	

	public static final String ID = "id";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SPRINT_ID")
	private int id;

	@NotBlank(message = "name is a required field")
	@Column(name = "NAME", nullable = false, length = 60)
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE", nullable = false)
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE", nullable = false)
	private Date endDate;
	
	@NotBlank(message = "daily scrum is a required field")
	@Column(name = "DAILY_SCRUM", nullable = false)
	private String dailyScrum;
	
	@NotBlank(message = "goal is a required field")
	@Column(name = "GOAL", nullable = false)
	private String goal;

	@ManyToOne
	@JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID")	
	private Project project;	

	public Sprint() { }		

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
	
	public String getDailyScrum() {
		return dailyScrum;
	}

	public void setDailyScrum(String dailyScrum) {
		this.dailyScrum = dailyScrum;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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

	private static final long serialVersionUID = 4897729582058383675L;

}

