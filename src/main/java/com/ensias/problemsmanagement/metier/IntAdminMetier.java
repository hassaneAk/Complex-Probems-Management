package com.ensias.problemsmanagement.metier;

import java.util.List;

import com.ensias.problemsmanagement.entities.*;

public interface IntAdminMetier extends IntMemberMetier {
	
	public List<User> listusers();
	public List<User> listMembers();
	public List<User> userParKeyword(String kw); 

	
	public User getUser(Long idUser); 
	
	public void supprimerProblem(Long idPr); 
	public void supprimerSProblem(Long idSPr); 
	public void supprimerReply(Long idRep); 
	public void supprimerUser(Long idUser); 
	
	//Archive----------------
	public List<User> listUserArchi(); 
	public List<Problem> listProbArchi(); 
	public List<Reply> listRepArchi(); 
	public List<SProblem> listSpArchi(); 
	public List<Problem> listProbUserArchi(Long idUser); 
	public List<SProblem> listSpUserArchi(Long idUser); 
	public List<SProblem> listSpPrArchi(Long idPr); 
	public List<Reply> listRepUserArchi(Long idUser);
	public List<Reply> listRepProbArchi(Long idPr);
	public List<Reply> listRepSpArchi(Long idSPr);
	public List<User> searchUserArchi(String keyword);
	public List<Problem> searchProbArchi(String keyword);
	public List<SProblem> searchSProbArchi(String keyword);
	
}

