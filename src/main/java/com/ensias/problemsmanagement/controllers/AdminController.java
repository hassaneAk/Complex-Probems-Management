package com.ensias.problemsmanagement.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ensias.problemsmanagement.entities.Problem;
import com.ensias.problemsmanagement.entities.Reply;
import com.ensias.problemsmanagement.entities.SProblem;
import com.ensias.problemsmanagement.entities.User;
import com.ensias.problemsmanagement.metier.IntAdminMetier;

@Controller
public class AdminController  {
	
	@Autowired
	private IntAdminMetier metier; 
	
	/* HOME PAGE */
	@RequestMapping(value="/admin/index", method = RequestMethod.GET)
	public String index(Model model, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			model.addAttribute("loggedUser", connectedUser); 
			return "adminindex"; 
		}
	}
	
	
	/* ---------------------------------PROBLEMS---------------------------------------------- */
	@RequestMapping(value="/admin/problems", method = RequestMethod.GET)
	public String problems(Model model, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			model.addAttribute("archive", false); 
			model.addAttribute("problems", metier.listproblems()); 
			return "adminproblems"; 
		}
	}
	
	
	
	@RequestMapping(value="/admin/searchProblem", method = RequestMethod.POST)
	public String searchProblem(Model model, @RequestParam String keyword, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			model.addAttribute("problems", metier.problemParKeyword(keyword)); 
			return "adminproblems"; 
		}
	}
	
	@RequestMapping(value="/admin/searchSProblem", method = RequestMethod.POST)
	public String searchSProblem(Model model, @RequestParam String keyword, @RequestParam Long idPr, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			model.addAttribute("sproblems", metier.sproblemParKeyword(keyword)); 
			model.addAttribute("problem", metier.getProblem(idPr)); 
			return "adminsproblem"; 
		}
	}
	
	@RequestMapping(value="/admin/searchSelfProblem", method = RequestMethod.POST)
	public String searchSelfProblem(Model model, @RequestParam String keyword, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			List<Problem> searchedProblems = metier.problemParKeyword(keyword);
			for(Problem p : searchedProblems) {
				if(!p.getUser().getIdUser().equals(connectedUser.getIdUser())){
					searchedProblems.remove(p);
				}
			}
			model.addAttribute("problems", searchedProblems); 
			return "adminselfproblems"; 
		}
	}
	
	@RequestMapping(value="/admin/myproblems", method = RequestMethod.GET)
	public String myproblems(Model model, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			model.addAttribute("problems", metier.problemParUser(connectedUser.getIdUser())); 
			return "adminselfproblems"; 
		}
	}
	
	@RequestMapping(value="/admin/afficheProblem", method = RequestMethod.GET)
	public String afficheProblem(Model model, Long idPr, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			Problem p = metier.getProblem(idPr); 
			List<Reply> replies = metier.replyParProblem(p.getIdPr());
			List<Reply> solutions = new ArrayList<Reply>(); 
			List<Reply> others = new ArrayList<Reply>(); 
			for(Reply r : replies) {
				if(r.isSolution()) {
					solutions.add(r);
				}else {
					others.add(r);	
				}
			}
			model.addAttribute("archive", false);
			model.addAttribute("pr", p ); 
			model.addAttribute("replies", others);
			model.addAttribute("solutions", solutions);
			model.addAttribute("nbrSolutions", replies.size());
			return "adminproblemdetail"; 
		}
	}
		
	
	@RequestMapping(value="/admin/deleteProblem", method = RequestMethod.GET)
	public String deleteProblem(Model model, Long idPr, HttpSession session) {  
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			Problem p = metier.getProblem(idPr);
			p.setEnabled(false);
			metier.modifierProblem(p); 
			
			List<SProblem> sproblems = metier.sproblemParProb(idPr); 
			List<Reply> replies = metier.replyParProblem(p.getIdPr());
			
			for(SProblem sp: sproblems) {
				sp.setEnabled(false);
				metier.modifierSProblem(sp);
			}
			for(Reply r: replies) {
				r.setEnabled(false);
				metier.modifierReply(r);
			}
			
			
			return "redirect:/admin/problems"; 
		}
	}
	
	@RequestMapping(value="/admin/deleteSelfProblem", method = RequestMethod.GET)
	public String deleteSelfProblem(Model model, Long idPr, HttpSession session) {  
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			Problem p = metier.getProblem(idPr);
			p.setEnabled(false);
			metier.modifierProblem(p);  
			
			List<SProblem> sproblems = metier.sproblemParProb(idPr); 
			List<Reply> replies = metier.replyParProblem(p.getIdPr());
			
			for(SProblem sp: sproblems) {
				sp.setEnabled(false);
				metier.modifierSProblem(sp);
			}
			for(Reply r: replies) {
				r.setEnabled(false);
				metier.modifierReply(r);
			}
			
			
			return "redirect:/admin/myproblems"; 
		}
	}
	
	@RequestMapping(value="/admin/deleteRepProblem", method = RequestMethod.GET)
	public String deleteRepProblem(Model model, Long idRep, HttpSession session) {  
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			Reply rep = metier.getReply(idRep);
			rep.setEnabled(false);
			metier.modifierReply(rep);
			Problem pr = rep.getProblem(); 	
			return "redirect:/admin/afficheProblem?idPr="+pr.getIdPr(); 
		}
	}
	
	@RequestMapping(value="/admin/deleteRepSProblem", method = RequestMethod.GET)
	public String deleteRepSProblem(Model model, Long idRep, HttpSession session) {  
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {

			Reply rep = metier.getReply(idRep);
			rep.setEnabled(false);
			metier.modifierReply(rep);

			SProblem spr = rep.getSproblem(); 
			return "redirect:/admin/afficheSProblemRep?idSPr="+spr.getIdSPr(); 
		}
	}
	
	
	@RequestMapping(value="/admin/saveSelfProb", method = RequestMethod.POST)
	public String saveSelfProb(@RequestParam String titlePr, @RequestParam String bodyPr, HttpSession session) {
		
		User user = (User) session.getAttribute("loggedUser"); 
		if(user == null) {
			return "redirect:/login"; 
		}else {
			Problem p = new Problem(); 
			p.setTitlePr(titlePr);
			p.setBodyPr(bodyPr);
			p.setDatePr(new Date());
			p.setEnabled(true);
			p.setUser(user);
			metier.ajouterProblem(p);
			return "redirect:/admin/myproblems";
		}
	}

	@RequestMapping(value="/admin/saveProb", method = RequestMethod.POST)
	public String saveProb(@RequestParam String titlePr, @RequestParam String bodyPr, HttpSession session) {
		
		User user = (User) session.getAttribute("loggedUser"); 
		if(user == null) {
			return "redirect:/login"; 
		}else {
			Problem p = new Problem(); 
			p.setTitlePr(titlePr);
			p.setBodyPr(bodyPr);
			p.setDatePr(new Date());
			p.setEnabled(true);
			p.setUser(user);
			
			metier.ajouterProblem(p);
			return "redirect:/admin/problems";
		}
	}
	

	@RequestMapping(value="/admin/saveRepPr", method = RequestMethod.POST)
	public String saveRepPr(@RequestParam String contentRep, @RequestParam Long idPr, HttpSession session) {
		User user = (User) session.getAttribute("loggedUser"); 
		if(user == null || !user.isAdmin()) {
			return "redirect:/login"; 
		}else {
			Problem problem = metier.getProblem(idPr);
			Reply rep = new Reply();
			rep.setContentRep(contentRep);
			rep.setDateRep(new Date());
			rep.setSolution(false);
			rep.setEnabled(true);
			rep.setProblem(problem);
			rep.setSproblem(null);
			rep.setUser(user);
			metier.ajouterReply(rep); 
			return "redirect:/admin/afficheProblem?idPr="+problem.getIdPr();
		}
	}

	//---- selectSolution?idRep
	@RequestMapping(value="/admin/selectSolutionProb", method = RequestMethod.GET)
	public String selectSolutionProb(Model model, HttpSession session, @RequestParam Long idRep) { 
		
		User u = (User) session.getAttribute("loggedUser"); 
		if(u == null || !u.isAdmin()) {
			return "redirect:/login"; 
		}else {	
			Reply rep = metier.getReply(idRep);
			Problem p = rep.getProblem(); 
			if(p.getUser().getIdUser().equals(u.getIdUser())) {
				rep.setSolution(true);
				metier.modifierReply(rep);
			}		
			return "redirect:/admin/afficheProblem?idPr="+p.getIdPr();} 
	}
	
	//---- selectSolution?idRep
		@RequestMapping(value="/admin/selectSolutionSProb", method = RequestMethod.GET)
		public String selectSolutionSProb(Model model, HttpSession session, @RequestParam Long idRep) { 
			
			User u = (User) session.getAttribute("loggedUser"); 
			if(u == null || !u.isAdmin()) {
				return "redirect:/login"; 
			}else {	
				Reply rep = metier.getReply(idRep);
				SProblem sp = rep.getSproblem(); 
				if(sp.getUser().getIdUser().equals(u.getIdUser())) {
					rep.setSolution(true);
					metier.modifierReply(rep);
				}		
				return "redirect:/admin/afficheSProblemRep?idSPr="+sp.getIdSPr(); }
		}
	
	/*-------------------------------------- Sous Problems ----------------------------------------*/
	
	@RequestMapping(value="/admin/afficheSProblem", method = RequestMethod.GET)
	public String afficheSProblem(Model model, Long idPr, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null  || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			model.addAttribute("archive", false);
			model.addAttribute("sproblems", metier.sproblemParProb(idPr));
			model.addAttribute("problem", metier.getProblem(idPr)); 
			return "adminsproblem"; 
		}
	}
	
	@RequestMapping(value="/admin/saveSProb", method = RequestMethod.POST)
	public String saveSProb(@RequestParam Long idPr, @RequestParam String titleSPr, @RequestParam String bodySPr, HttpSession session) {
		User user = (User) session.getAttribute("loggedUser"); 
		if(user == null  || !user.isAdmin()) {
			return "redirect:/login"; 
		}else {
			Problem prob  = metier.getProblem(idPr); 
			SProblem sp = new SProblem(); 
			sp.setTitleSPr(titleSPr);
			sp.setBodySPr(bodySPr);
			sp.setDateSPr(new Date());
			sp.setEnabled(true);
			sp.setProblem(prob);
			sp.setUser(user);
			metier.ajouterSProblem(sp);
			return "redirect:/admin/afficheSProblem?idPr="+idPr;
		}
	}
	
	@RequestMapping(value="/admin/deleteSProblem", method = RequestMethod.GET)
	public String deleteSProblem(Model model, Long idSPr, HttpSession session) {
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			SProblem sp = metier.getSProblem(idSPr); 
			sp.setEnabled(false);
			metier.modifierSProblem(sp);

			List<Reply> replies = metier.replyParSProblem(sp.getIdSPr());
			for(Reply r : replies) {
				r.setEnabled(false);
				metier.modifierReply(r);
			}
			
			return "redirect:/admin/afficheSProblem?idPr="+sp.getProblem().getIdPr();
		}
	}
	
	
	@RequestMapping(value="/admin/afficheSProblemRep", method = RequestMethod.GET)
	public String afficheSProblemRep(Model model, Long idSPr, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			SProblem sp = metier.getSProblem(idSPr); 
			
			List<Reply> replies = metier.replyParSProblem(sp.getIdSPr());
			List<Reply> solutions = new ArrayList<Reply>(); 
			List<Reply> others = new ArrayList<Reply>(); 
			for(Reply r : replies) {
				if(r.isSolution()) {
					solutions.add(r);
				}else {
					others.add(r);	
				}
			}
			model.addAttribute("archive", false);
			model.addAttribute("spr", sp ); 
			model.addAttribute("replies", others);
			model.addAttribute("solutions", solutions);
			model.addAttribute("nbrSolutions", replies.size());
			
			return "adminsproblemdetail"; 
		}
	}
	

	@RequestMapping(value="/admin/saveRepSPr", method = RequestMethod.POST)
	public String saveRepSPr(@RequestParam String contentRep, @RequestParam Long idPr, @RequestParam Long idSPr, HttpSession session) {
		User user = (User) session.getAttribute("loggedUser"); 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			Reply rep = new Reply();
			rep.setContentRep(contentRep);
			rep.setDateRep(new Date());
			rep.setSolution(false);
			rep.setEnabled(true);
			rep.setProblem(metier.getProblem(idPr));
			rep.setSproblem(metier.getSProblem(idSPr));
			rep.setUser(connectedUser);
			metier.ajouterReply(rep); 
			return "redirect:/admin/afficheSProblemRep?idSPr="+idSPr;
		}
	}
	
	/*-------------------------------------- USERS ----------------------------------------*/
	@RequestMapping(value="/admin/users", method = RequestMethod.GET)
	public String users(Model model, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			model.addAttribute("archive", false); 
			model.addAttribute("members", metier.listMembers()); 
			return "adminusers"; 
		}
	}
	
	@RequestMapping(value="/admin/searchUser", method = RequestMethod.POST)
	public String searchUser(Model model, @RequestParam String keyword, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			model.addAttribute("members", metier.userParKeyword(keyword)); 
			return "adminusers"; 
		}
	}
	
	@RequestMapping(value="/admin/userdetail", method = RequestMethod.GET)
	public String userdetail(Model model, Long idUser, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else { 
			List<Problem> problems = metier.problemParUser(idUser);
			List<SProblem> sproblems = metier.sproblemParUser(idUser); 
			List<Reply> replies = metier.replyParUser(idUser); 
		
			model.addAttribute("u", metier.getUser(idUser)); 
			
			model.addAttribute("problems", problems); 
			model.addAttribute("nbrpr", problems.size()); 
			
			model.addAttribute("sproblems", sproblems); 
			model.addAttribute("nbrspr", sproblems.size()); 
			
			model.addAttribute("replies", replies); 
			model.addAttribute("nbrrep", replies.size()); 
			
			return "adminuserdetail"; 
		}
	}

	@RequestMapping(value="/admin/delUser", method = RequestMethod.GET)
	public String delUser(Model model, Long idUser, HttpSession session) {
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			User membre = metier.getUser(idUser);
			membre.setEnabled(false);
			metier.modifierUser(membre); 
			
			List<Problem> problems = metier.problemParUser(idUser);
			List<SProblem> sproblems = metier.sproblemParUser(idUser); 
			List<Reply> replies = metier.replyParUser(idUser); 
			
			for(Problem p : problems) {
				p.setEnabled(false);
				metier.modifierProblem(p);
			}
			for(SProblem sp: sproblems) {
				sp.setEnabled(false);
				metier.modifierSProblem(sp);
			}
			for(Reply r: replies) {
				r.setEnabled(false);
				metier.modifierReply(r);
			}
			
			return "redirect:/admin/users"; 
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("loggedUser", null);
		return "home";
	}
	
	
	//-------Update user
	@RequestMapping(value="/admin/updateUser", method = RequestMethod.POST)
	public String updateUser(@RequestParam String firstname, @RequestParam String lastname, @RequestParam String mail, HttpSession session) {
		User u = (User) session.getAttribute("loggedUser"); 
		if(u == null || !u.isAdmin()) {
			return "redirect:/login"; 
		}else {	
			u.setFirstName(firstname);
			u.setLastName(lastname);
			u.setMail(mail);
			metier.modifierUser(u);
			return "redirect:/admin/index"; 
		}
	}
	
	//----- Archives
	@RequestMapping(value="/admin/archivemember", method = RequestMethod.GET)
	public String archivemember(Model model, HttpSession session) {
		User u = (User) session.getAttribute("loggedUser"); 
		if(u == null || !u.isAdmin()) {
			return "redirect:/login"; 
		}else {	
			model.addAttribute("archive", true); 
			model.addAttribute("members", metier.listUserArchi()); 
			return "adminusers"; // --- A ajouter
		}
	}
	
	@RequestMapping(value="/admin/archiveproblem", method = RequestMethod.GET)
	public String archiveproblem(Model model, HttpSession session) {
		User u = (User) session.getAttribute("loggedUser"); 
		if(u == null || !u.isAdmin()) {
			return "redirect:/login"; 
		}else {	
			model.addAttribute("archive", true); 
			model.addAttribute("problems", metier.listProbArchi()); 
			return "adminproblems"; 
		}
	}
	
	@RequestMapping(value="/admin/repOfArchiPr", method = RequestMethod.GET)
	public String repOfArchiPr(Model model, Long idPr, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			Problem p = metier.getProblem(idPr); 
			List<Reply> replies = metier.listRepProbArchi(p.getIdPr());
			List<Reply> solutions = new ArrayList<Reply>(); 
			List<Reply> others = new ArrayList<Reply>(); 
			for(Reply r : replies) {
				if(r.isSolution()) {
					solutions.add(r);
				}else {
					others.add(r);	
				}
			}
			model.addAttribute("archive", true);
			model.addAttribute("pr", p ); 
			model.addAttribute("replies", others);
			model.addAttribute("solutions", solutions);
			model.addAttribute("nbrSolutions", replies.size());
			return "adminproblemdetail"; 
		}
	}
	
	@RequestMapping(value="/admin/repOfArchiSpr", method = RequestMethod.GET)
	public String repOfArchiSpr(Model model, Long idSPr, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			SProblem sp = metier.getSProblem(idSPr); 
			
			List<Reply> replies = metier.listRepSpArchi(sp.getIdSPr());
			List<Reply> solutions = new ArrayList<Reply>(); 
			List<Reply> others = new ArrayList<Reply>(); 
			for(Reply r : replies) {
				if(r.isSolution()) {
					solutions.add(r);
				}else {
					others.add(r);	
				}
			}
			model.addAttribute("archive", true);
			model.addAttribute("spr", sp ); 
			model.addAttribute("replies", others);
			model.addAttribute("solutions", solutions);
			model.addAttribute("nbrSolutions", replies.size());
			
			return "adminsproblemdetail"; 
		}
	}
	
	@RequestMapping(value="/admin/sprOfArchiPr", method = RequestMethod.GET)
	public String sprOfArchiPr(Model model, Long idPr, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null  || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			model.addAttribute("archive", true);
			model.addAttribute("sproblems", metier.listSpPrArchi(idPr));
			model.addAttribute("problem", metier.getProblem(idPr)); 
			return "adminsproblem"; 
		}
	}
	
	@RequestMapping(value="/admin/archivesproblem", method = RequestMethod.GET)
	public String archivesproblem(Model model, HttpSession session) {
		User u = (User) session.getAttribute("loggedUser"); 
		if(u == null || !u.isAdmin()) {
			return "redirect:/login"; 
		}else {	 
			model.addAttribute("sproblems", metier.listSpArchi()); 
			return "archivesp"; 
		}
	}
	
	@RequestMapping(value="/admin/archivesreply", method = RequestMethod.GET)
	public String archivesreply(Model model, HttpSession session) {
		User u = (User) session.getAttribute("loggedUser"); 
		if(u == null || !u.isAdmin()) {
			return "redirect:/login"; 
		}else {	 
			model.addAttribute("replies", metier.listRepArchi()); 
			return "archiverep"; 
		}
	}
	
	@RequestMapping(value="/admin/searchArchiUser", method = RequestMethod.POST)
	public String searchArchiUser(Model model, @RequestParam String keyword, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			model.addAttribute("archive", true);
			model.addAttribute("members", metier.searchUserArchi(keyword)); 
			return "adminusers"; 
		}
	}
	
	@RequestMapping(value="/admin/searchArchiProblem", method = RequestMethod.POST)
	public String searchArchiProblem(Model model, @RequestParam String keyword, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			model.addAttribute("archive", true); 
			model.addAttribute("problems", metier.searchProbArchi(keyword)); 
			return "adminproblems"; 
		}
	}
	
	@RequestMapping(value="/admin/searchArchiSProblem", method = RequestMethod.POST)
	public String searchArchiSProblem(Model model, @RequestParam String keyword, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || !connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			model.addAttribute("archive", true); 
			model.addAttribute("sproblems", metier.searchSProbArchi(keyword)); 
			return "archivesp"; 
		}
	}
}



























