package com.ensias.problemsmanagement.metier;

import java.util.List;

import com.ensias.problemsmanagement.entities.*;

public interface IntMemberMetier {
	
	public List<Problem> listproblems();
	public List<SProblem> listsproblems();
	public List<Reply> listreplies();
	
	public List<Problem> problemParKeyword(String kw);
	public List<Problem> problemParUser(Long idUser); 
	public List<SProblem> sproblemParKeyword(String kw);
	public List<SProblem> sproblemParUser(Long idUser); 
	public List<SProblem> sproblemParProb(Long idPr);
	public List<Reply> replyParKeyword(String kw); 
	public List<Reply> replyParProblem(Long idPr); 
	public List<Reply> replyParSProblem(Long idSPr); 
	public List<Reply> replyParUser(Long idUser); 
	
	public Problem getProblem(Long idPr);
	public SProblem getSProblem(Long idSPr);
	public Reply getReply(Long idRep);
	
	public Long ajouterProblem(Problem p); 
	public Long ajouterSProblem(SProblem sp); 
	public Long ajouterReply(Reply reply);
	
	public void modifierProblem(Problem problem); 
	public void modifierSProblem(SProblem sproblem);
	public void modifierReply(Reply reply); 
	public void modifierUser(User user); 
	
	public void setSolutionProblem(Reply reply); 
	public void setSolutionSProblem(Reply reply); 
}
