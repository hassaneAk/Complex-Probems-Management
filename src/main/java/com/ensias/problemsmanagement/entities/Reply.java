package com.ensias.problemsmanagement.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="Reply")
public class Reply implements Serializable{
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idRep;
		
		@NotNull
	private String contentRep; 
	
		@NotNull
	private Date dateRep; 
	
		@NotNull
	private boolean solution;

	private boolean enabled; 
	
		@ManyToOne
		@JoinColumn(name="idPr")
		@NotNull
	private Problem problem; 
		
		@ManyToOne
		@JoinColumn(name="idSPr")
	private SProblem sproblem; 
	
		@ManyToOne
		@JoinColumn(name="idUser")
		@NotNull
	private User user;
	
	
	//-------------------------------------------------------------------------
	public Reply() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reply(String contentRep, Date dateRep, boolean solution, Problem problem, User user) {
		super();
		this.contentRep = contentRep;
		this.dateRep = dateRep;
		this.solution = solution;
		this.problem = problem;
		this.user = user;
	}
	
	
	//-------------------------------------------------------------------------
	public Long getIdRep() {
		return idRep;
	}
	public void setIdRep(Long idRep) {
		this.idRep = idRep;
	}
	public String getContentRep() {
		return contentRep;
	}
	public void setContentRep(String contentRep) {
		this.contentRep = contentRep;
	}
	public Date getDateRep() {
		return dateRep;
	}
	public void setDateRep(Date dateRep) {
		this.dateRep = dateRep;
	}
	public boolean isSolution() {
		return solution;
	}
	public void setSolution(boolean solution) {
		this.solution = solution;
	}
	public Problem getProblem() {
		return problem;
	}
	public void setProblem(Problem problem) {
		this.problem = problem;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public SProblem getSproblem() {
		return sproblem;
	}
	public void setSproblem(SProblem sproblem) {
		this.sproblem = sproblem;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
