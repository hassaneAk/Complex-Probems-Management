package com.ensias.problemsmanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ensias.problemsmanagement.entities.Problem;
import com.ensias.problemsmanagement.entities.Reply;
import com.ensias.problemsmanagement.entities.SProblem;
import com.ensias.problemsmanagement.entities.User;


public class ProblemDaoImp implements IntProblemDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Long ajouterProblem(Problem p) {
		em.persist(p);
		return p.getIdPr();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Problem> listproblems() {
		Query req = em.createQuery("select p from Problem p where p.enabled=true order by p.datePr desc");
		return req.getResultList();
	}

	@Override
	public List<Problem> problemParKeyword(String kw) {
		Query req = em.createQuery("select p from Problem p where (p.titlePr like :x or p.bodyPr like :x) and p.enabled=true order by p.datePr desc");
		req.setParameter("x", "%"+kw+"%" ); 
		return req.getResultList();
	}

	@Override
	public List<Problem> problemParUser(Long idUser) {
		Query req = em.createQuery("select p from Problem p where p.user.idUser = :x and p.enabled=true order by p.datePr desc");
		req.setParameter("x", idUser ); 
		return req.getResultList();
	}

	@Override
	public Problem getProblem(Long idPr) {
		return em.find(Problem.class, idPr);
	}

	@Override
	public void supprimerProblem(Long idPr) {
		List<Reply> replies = replyParProblem(idPr); 
		for(Reply rep : replies) {
			em.remove(rep); 
		}
		List<SProblem> sproblems = sproblemParProblem(idPr); 
		for(SProblem sp : sproblems) {
			em.remove(sp); 
		}
		Problem pr = getProblem(idPr);
		em.remove(pr);
	}

	@Override
	public void modifierProblem(Problem problem) {
		em.merge(problem);
	}

	@Override
	public Long ajouterSProblem(SProblem sp) {
		em.persist(sp);
		return sp.getIdSPr();
	}

	@Override
	public List<SProblem> listsproblems() {
		Query req = em.createQuery("select sp from SProblem sp where sp.enabled=1 order by sp.dateSPr desc");
		return req.getResultList();
	}

	@Override
	public List<SProblem> sproblemParKeyword(String kw) {
		Query req = em.createQuery("select sp from SProblem sp where (sp.titleSPr like :x or sp.bodySPr like :x) and sp.enabled=1 order by sp.dateSPr desc ");
		req.setParameter("x", "%"+kw+"%" ); 
		return req.getResultList();
	}

	@Override
	public List<SProblem> sproblemParUser(Long idUser) {
		Query req = em.createQuery("select sp from SProblem sp where sp.user.idUser = :x  and sp.enabled=1 order by sp.dateSPr desc");
		req.setParameter("x", idUser); 
		return req.getResultList();
	}

	@Override
	public List<SProblem> sproblemParProblem(Long idPr) {
		Query req = em.createQuery("select sp from SProblem sp where sp.problem.idPr = :x and sp.enabled=1 order by sp.dateSPr desc");
		req.setParameter("x", idPr); 
		return req.getResultList();
	}

	@Override
	public SProblem getSProblem(Long idSPr) {
		return em.find(SProblem.class, idSPr);
	}

	@Override
	public void supprimerSProblem(Long idSPr) {
		List<Reply> replies = replyParSProblem(idSPr); 
		for(Reply rep : replies) {
			em.remove(rep); 
		}
		SProblem spr = getSProblem(idSPr);
		em.remove(spr);
	}

	@Override
	public void modifierSProblem(SProblem sproblem) {
		em.merge(sproblem);
	}

	@Override
	public Long ajouterReply(Reply reply) {
		em.persist(reply);
		return reply.getIdRep();
	}

	@Override
	public List<Reply> listreplies() {
		Query req = em.createQuery("select rep from Reply rep where rep.enabled=true order by rep.dateRep desc");
		return req.getResultList();
	}

	@Override
	public List<Reply> replyParKeyword(String kw) {
		Query req = em.createQuery("select rep from Reply rep where rep.contentRep like :x and rep.enabled=1 order by rep.dateRep desc");
		req.setParameter("x", "%"+kw+"%" ); 
		return req.getResultList();
	}

	@Override
	public List<Reply> replyParProblem(Long idPr) {
		Query req = em.createQuery("select rep from Reply rep where rep.problem.idPr = :x and rep.enabled=1 order by rep.dateRep desc");
		req.setParameter("x", idPr); 
		return req.getResultList();
	}

	@Override
	public List<Reply> replyParSProblem(Long idSPr) {
		Query req = em.createQuery("select rep from Reply rep where rep.sproblem.idSPr = :x and rep.enabled=1 order by rep.dateRep desc");
		req.setParameter("x", idSPr); 
		return req.getResultList();
	}

	@Override
	public List<Reply> replyParUser(Long idUser) {
		Query req = em.createQuery("select rep from Reply rep where rep.user.idUser = :x and rep.enabled=1 order by rep.dateRep desc");
		req.setParameter("x", idUser); 
		return req.getResultList();
	}

	@Override
	public Reply getReply(Long idRep) {
		return em.find(Reply.class, idRep);
	}

	@Override
	public void supprimerReply(Long idRep) {
		Reply rep = getReply(idRep);
		em.remove(rep);
	}

	@Override
	public void modifierReply(Reply reply) {
		em.merge(reply);
	}

	@Override
	public Long ajouterUser(User user) {
		em.persist(user);
		return user.getIdUser();
	}

	@Override
	public User getUser(Long idUser) {
		return em.find(User.class, idUser);
	}


	@Override
	public void setSolutionProblem(Reply reply) {
		reply.setSolution(true);
		modifierReply(reply);
	}


	@Override
	public void setSolutionSProblem(Reply reply) {
		reply.setSolution(true);
		modifierReply(reply);
	}


	@Override
	public List<User> listusers() {
		Query req = em.createQuery("select u from User u where u.enabled=true order by u.lastName desc");
		return req.getResultList();
	}


	@Override
	public List<User> userParKeyword(String kw) {
		Query req = em.createQuery("select u from User u where (u.firstName like :x or u.lastName like :x) and u.enabled=true and u.admin=false order by u.lastName desc");
		req.setParameter("x",  "%"+kw+"%" ); 
		return req.getResultList();
	}


	@Override
	public void supprimerUser(Long idUser) {
		User user = getUser(idUser); 
		List<Reply> replies = replyParUser(idUser); 
		for(Reply rep : replies) {
			em.remove(rep); 
		}
		List<SProblem> sproblems = sproblemParUser(idUser); 
		for(SProblem sp : sproblems) {
			em.remove(sp); 
		}
		List<Problem> problems = problemParUser(idUser); 
		for(Problem p : problems) {
			em.remove(p); 
		}
		em.remove(user);
	}

	@Override
	public User userParLogin(String login) {
		Query req = em.createQuery("select u from User u where u.login like :x and u.enabled=true");
		req.setParameter("x", login); 
		return (User) req.getResultList();
	}

	@Override
	public List<User> listMembers() {
		Query req = em.createQuery("select u from User u where u.admin=false and u.enabled=true order by u.lastName desc");
		return req.getResultList();
	}


	@Override
	public void modifierUser(User user) {
		em.merge(user);
		
	}


	@Override
	public List<User> listUserArchi() {
		Query req = em.createQuery("select u from User u where u.admin = 0 and u.enabled=false order by u.lastName desc");
		return req.getResultList();
	}


	@Override
	public List<Problem> listProbArchi() {
		Query req = em.createQuery("select p from Problem p where p.enabled=false order by p.datePr desc");
		return req.getResultList();
	}


	@Override
	public List<Reply> listRepArchi() {
		Query req = em.createQuery("select rep from Reply rep where rep.enabled=false order by rep.dateRep desc");
		return req.getResultList();
	}


	@Override
	public List<SProblem> listSpArchi() {
		Query req = em.createQuery("select sp from SProblem sp where sp.enabled=false order by sp.dateSPr desc");
		return req.getResultList();
	}


	@Override
	public List<Problem> listProbUserArchi(Long idUser) {
		Query req = em.createQuery("select p from Problem p where p.enabled=false and p.user.idUser=:x order by p.datePr desc");
		req.setParameter("x", idUser); 
		return req.getResultList();
	}


	@Override
	public List<SProblem> listSpUserArchi(Long idUser) {
		Query req = em.createQuery("select sp from SProblem sp where sp.enabled=false and sp.user.idUser=:x order by sp.dateSPr desc");
		req.setParameter("x", idUser); 
		return req.getResultList();
	}


	@Override
	public List<Reply> listRepUserArchi(Long idUser) {
		Query req = em.createQuery("select rep from Reply rep where rep.enabled=false and rep.user.idUser=:x order by rep.dateRep desc");
		req.setParameter("x", idUser); 
		return req.getResultList();
	}


	@Override
	public List<Reply> listRepProbArchi(Long idPr) {
		Query req = em.createQuery("select rep from Reply rep where rep.enabled=false and rep.problem.idPr=:x order by rep.dateRep desc");
		req.setParameter("x", idPr); 
		return req.getResultList();
	}


	@Override
	public List<Reply> listRepSpArchi(Long idSPr) {
		Query req = em.createQuery("select rep from Reply rep where rep.enabled=false and rep.sproblem.idSPr=:x order by rep.dateRep desc");
		req.setParameter("x", idSPr); 
		return req.getResultList();
	}


	@Override
	public List<User> searchUserArchi(String keyword) {
		Query req = em.createQuery("select u from User u where u.enabled=false and (u.firstName like :x or u.lastName like :x) order by u.lastName desc ");
		req.setParameter("x", "%"+keyword+"%"); 
		return req.getResultList();
	}


	@Override
	public List<Problem> searchProbArchi(String keyword) {
		Query req = em.createQuery("select p from Problem p where p.enabled=false and p.titlePr like :x ");
		req.setParameter("x", "%"+keyword+"%"); 
		return req.getResultList();
	}


	@Override
	public List<SProblem> searchSProbArchi(String keyword) {
		Query req = em.createQuery("select sp from SProblem sp where sp.enabled=false and sp.titleSPr like :x");
		req.setParameter("x", "%"+keyword+"%"); 
		return req.getResultList();
	}


	@Override
	public List<SProblem> listSpPrArchi(Long idPr) {
		Query req = em.createQuery("select sp from SProblem sp where sp.problem.idPr = :x order by sp.dateSPr desc");
		req.setParameter("x", idPr); 
		return req.getResultList();
	}
	
}
