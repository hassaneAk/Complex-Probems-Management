package com.ensias.problemsmanagement.metier;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ensias.problemsmanagement.dao.IntProblemDAO;
import com.ensias.problemsmanagement.entities.*;

@Transactional
public class ProblemMetierImpl implements IntAdminMetier, IntAuthentificationMetier{

	private IntProblemDAO dao;
	
	public void setDao(IntProblemDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<Problem> listproblems() {
		// TODO Auto-generated method stub
		return dao.listproblems();
	}

	@Override
	public List<SProblem> listsproblems() {
		// TODO Auto-generated method stub
		return dao.listsproblems();
	}

	@Override
	public List<Reply> listreplies() {
		// TODO Auto-generated method stub
		return dao.listreplies();
	}

	@Override
	public List<Problem> problemParKeyword(String kw) {
		// TODO Auto-generated method stub
		return dao.problemParKeyword(kw);
	}

	@Override
	public List<Problem> problemParUser(Long idUser) {
		// TODO Auto-generated method stub
		return dao.problemParUser(idUser);
	}

	@Override
	public List<SProblem> sproblemParKeyword(String kw) {
		// TODO Auto-generated method stub
		return dao.sproblemParKeyword(kw);
	}

	@Override
	public List<SProblem> sproblemParUser(Long idUser) {
		// TODO Auto-generated method stub
		return dao.sproblemParUser(idUser);
	}

	@Override
	public List<Reply> replyParKeyword(String kw) {
		// TODO Auto-generated method stub
		return dao.replyParKeyword(kw);
	}

	@Override
	public List<Reply> replyParProblem(Long idPr) {
		// TODO Auto-generated method stub
		return dao.replyParProblem(idPr);
	}

	@Override
	public Problem getProblem(Long idPr) {
		// TODO Auto-generated method stub
		return dao.getProblem(idPr);
	}

	@Override
	public SProblem getSProblem(Long idSPr) {
		// TODO Auto-generated method stub
		return dao.getSProblem(idSPr);
	}

	@Override
	public Reply getReply(Long idRep) {
		// TODO Auto-generated method stub
		return dao.getReply(idRep);
	}

	@Override
	public Long ajouterProblem(Problem p) {
		// TODO Auto-generated method stub
		return dao.ajouterProblem(p);
	}

	@Override
	public Long ajouterSProblem(SProblem sp) {
		// TODO Auto-generated method stub
		return dao.ajouterSProblem(sp);
	}
	
	@Override
	public Long ajouterReply(Reply reply) {
		// TODO Auto-generated method stub
		return dao.ajouterReply(reply);
	}

	@Override
	public void modifierProblem(Problem problem) {
		// TODO Auto-generated method stub
		dao.modifierProblem(problem);
	}

	@Override
	public void modifierSProblem(SProblem sproblem) {
		// TODO Auto-generated method stub
		dao.modifierSProblem(sproblem);
	}

	@Override
	public void modifierReply(Reply reply) {
		// TODO Auto-generated method stub
		dao.modifierReply(reply);
	}

	@Override
	public void setSolutionProblem(Reply reply) {
		// TODO Auto-generated method stub
		dao.setSolutionProblem(reply);
	}

	@Override
	public void setSolutionSProblem(Reply reply) {
		// TODO Auto-generated method stub
		dao.setSolutionSProblem(reply);
	}

	@Override
	public List<User> listusers() {
		// TODO Auto-generated method stub
		return dao.listusers();
	}

	@Override
	public List<User> userParKeyword(String kw) {
		// TODO Auto-generated method stub
		return dao.userParKeyword(kw);
	}

	@Override
	public User getUser(Long idUser) {
		// TODO Auto-generated method stub
		return dao.getUser(idUser);
	}

	@Override
	public void supprimerProblem(Long idPr) {
		// TODO Auto-generated method stub
		dao.supprimerProblem(idPr);
	}

	@Override
	public void supprimerSProblem(Long idSPr) {
		// TODO Auto-generated method stub
		dao.supprimerSProblem(idSPr);
	}

	@Override
	public void supprimerReply(Long idRep) {
		// TODO Auto-generated method stub
		dao.supprimerReply(idRep);
	}

	@Override
	public void supprimerUser(Long idUser) {
		// TODO Auto-generated method stub
		dao.supprimerUser(idUser);
	}

	@Override
	public User userParLogin(String login) {
		return dao.userParLogin(login);
	}

	@Override
	public void addUser(User user) {
		dao.ajouterUser(user); 
	}

	@Override
	public List<User> listMembers() {
		return dao.listMembers();
	}

	@Override
	public List<SProblem> sproblemParProb(Long idPr) {
		return dao.sproblemParProblem(idPr);
	}

	@Override
	public List<Reply> replyParSProblem(Long idSPr) {
		return dao.replyParSProblem(idSPr);
	}

	@Override
	public List<Reply> replyParUser(Long idUser) {
		return dao.replyParUser(idUser);
	}

	@Override
	public void modifierUser(User user) {
		dao.modifierUser(user);
	}

	@Override
	public List<User> listUserArchi() {
		return dao.listUserArchi();
	}

	@Override
	public List<Problem> listProbArchi() {
		// TODO Auto-generated method stub
		return dao.listProbArchi();
	}

	@Override
	public List<Reply> listRepArchi() {
		// TODO Auto-generated method stub
		return dao.listRepArchi();
	}

	@Override
	public List<SProblem> listSpArchi() {
		// TODO Auto-generated method stub
		return dao.listSpArchi();
	}

	@Override
	public List<Problem> listProbUserArchi(Long idUser) {
		// TODO Auto-generated method stub
		return dao.listProbUserArchi(idUser);
	}

	@Override
	public List<SProblem> listSpUserArchi(Long idUser) {
		// TODO Auto-generated method stub
		return dao.listSpUserArchi(idUser);
	}
	
	@Override
	public List<SProblem> listSpPrArchi(Long idPr) {
		// TODO Auto-generated method stub
		return dao.listSpPrArchi(idPr);
	}

	@Override
	public List<Reply> listRepUserArchi(Long idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reply> listRepProbArchi(Long idPr) {
		// TODO Auto-generated method stub
		return dao.listRepProbArchi(idPr);
	}

	@Override
	public List<Reply> listRepSpArchi(Long idSPr) {
		// TODO Auto-generated method stub
		return dao.listRepSpArchi(idSPr);
	}

	@Override
	public List<User> searchUserArchi(String keyword) {
		// TODO Auto-generated method stub
		return dao.searchUserArchi(keyword);
	}

	@Override
	public List<Problem> searchProbArchi(String keyword) {
		// TODO Auto-generated method stub
		return dao.searchProbArchi(keyword);
	}

	@Override
	public List<SProblem> searchSProbArchi(String keyword) {
		// TODO Auto-generated method stub
		return dao.searchSProbArchi(keyword);
	}

}
