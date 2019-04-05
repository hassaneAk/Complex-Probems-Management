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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="SProblem")
public class SProblem implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idSPr; 
	
	@NotNull
	private String titleSPr; 
	
	@NotNull
	private String bodySPr;
	
	@NotNull
	private Date dateSPr;
	
	private boolean enabled; 

	//Clé étrangère réf User
		@ManyToOne
		@JoinColumn(name="idPr")
		@NotNull
	private Problem problem; 
	
		//Clé étrangère réf User
		@ManyToOne
		@JoinColumn(name="idUser")
		@NotNull
	private User user; 
	
		@OneToMany(mappedBy="sproblem")
	private Collection<Reply> replies;

		public Long getIdSPr() {
			return idSPr;
		}

		public void setIdSPr(Long idSPr) {
			this.idSPr = idSPr;
		}

		public String getTitleSPr() {
			return titleSPr;
		}

		public void setTitleSPr(String titleSPr) {
			this.titleSPr = titleSPr;
		}

		public String getBodySPr() {
			return bodySPr;
		}

		public void setBodySPr(String bodySPr) {
			this.bodySPr = bodySPr;
		}

		public Date getDateSPr() {
			return dateSPr;
		}

		public void setDateSPr(Date dateSPr) {
			this.dateSPr = dateSPr;
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

		public Collection<Reply> getReplies() {
			return replies;
		}

		public void setReplies(Collection<Reply> replies) {
			this.replies = replies;
		}

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
		
	//-------------------------------------------------------------------------
	
	
	
}
