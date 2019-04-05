package com.ensias.problemsmanagement.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Problem")
public class Problem implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPr; 
	
	private String titlePr; 
	
	private String bodyPr;
	
	private Date datePr;
	
	private boolean enabled; 

	@ManyToOne
	@JoinColumn(name="idUser")
	private User user; 
	
	@OneToMany(mappedBy="problem")
	private Collection<SProblem> sproblems;
	
	@OneToMany(mappedBy="problem")
	private Collection<Reply> replies;
	
	//-------------------------------------------------------------------------
	public Problem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Problem(String titlePr, String bodyPr, Date datePr, User user) {
		super();
		this.titlePr = titlePr;
		this.bodyPr = bodyPr;
		this.datePr = datePr;
		this.user = user;
	}

	
	
	//-------------------------------------------------------------------------
	public Long getIdPr() {
		return idPr;
	}
	public void setIdPr(Long idPr) {
		this.idPr = idPr;
	}
	public String getTitlePr() {
		return titlePr;
	}
	public void setTitlePr(String titlePr) {
		this.titlePr = titlePr;
	}
	public String getBodyPr() {
		return bodyPr;
	}
	public void setBodyPr(String bodyPr) {
		this.bodyPr = bodyPr;
	}
	public Date getDatePr() {
		return datePr;
	}
	public void setDatePr(Date datePr) {
		this.datePr = datePr;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
}
