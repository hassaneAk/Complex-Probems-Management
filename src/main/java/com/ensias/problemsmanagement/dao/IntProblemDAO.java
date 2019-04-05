package com.ensias.problemsmanagement.dao;

import java.util.List;

import com.ensias.problemsmanagement.entities.*;

public interface IntProblemDAO {
	
	//Méthode de management des problemes
	public Long ajouterProblem(Problem p); 
	public List<Problem> listproblems();
	public List<Problem> problemParKeyword(String kw);
	public List<Problem> problemParUser(Long idUser); 
	public Problem getProblem(Long idPr);
	public void supprimerProblem(Long idPr); 
	public void modifierProblem(Problem problem); 
	public void setSolutionProblem(Reply reply); 
	
	//Méthode de management des sous problemes
	public Long ajouterSProblem(SProblem sp); 
	public List<SProblem> listsproblems();
	public List<SProblem> sproblemParKeyword(String kw);
	public List<SProblem> sproblemParUser(Long idUser); 
	public List<SProblem> sproblemParProblem(Long idPr); 
	public SProblem getSProblem(Long idSPr);
	public void supprimerSProblem(Long idSPr); 
	public void modifierSProblem(SProblem sproblem);
	public void setSolutionSProblem(Reply reply); 
	
	//Méthode de management des replies
	public Long ajouterReply(Reply reply); 
	public List<Reply> listreplies();
	public List<Reply> replyParKeyword(String kw); 
	public List<Reply> replyParProblem(Long idPr); 
	public List<Reply> replyParSProblem(Long idSPr); 
	public List<Reply> replyParUser(Long idUser);
	public Reply getReply(Long idRep);
	public void supprimerReply(Long idRep); 
	public void modifierReply(Reply reply); 
	
	//Méthode de management des users
	public List<User> listusers(); 
	public List<User> listMembers(); 
	public List<User> userParKeyword(String kw); 
	public User userParLogin(String login); 
	public Long ajouterUser(User user); 
	public User getUser(Long idUser); 
	public void supprimerUser(Long idUser);
	public void modifierUser(User user); 
	
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
