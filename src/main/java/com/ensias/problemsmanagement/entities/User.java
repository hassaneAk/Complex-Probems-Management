package com.ensias.problemsmanagement.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="User")
public class User implements Serializable {
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUser;
		
	private String firstName;
	
	private String lastName;
	
	private String mail;
	
	private boolean admin; 
	
	private boolean enabled; 
		
		@NotNull
	private String login; 
	
		@NotNull
	private String mdp; 
	
		@OneToMany(mappedBy="user")
	private Collection<Problem> problems; 

		@OneToMany(mappedBy="user")
	private Collection<SProblem> sproblems; 

		@OneToMany(mappedBy="user")
	private Collection<Reply> replies;

	//---------------------------------------------------------
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String firstName, String lastName, String mail, String login, String mdp) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.login = login;
		this.mdp = mdp;
	}
	
	
	//---------------------------------------------------------
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public Collection<Problem> getProblems() {
		return problems;
	}
	public void setProblems(Collection<Problem> problems) {
		this.problems = problems;
	}
	public Collection<Reply> getReplies() {
		return replies;
	}
	public void setReplies(Collection<Reply> replies) {
		this.replies = replies;
	}
	public Collection<SProblem> getSproblems() {
		return sproblems;
	}
	public void setSproblems(Collection<SProblem> sproblems) {
		this.sproblems = sproblems;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
