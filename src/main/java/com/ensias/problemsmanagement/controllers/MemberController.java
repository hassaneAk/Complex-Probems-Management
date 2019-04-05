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
import com.ensias.problemsmanagement.metier.IntMemberMetier;

@Controller
public class MemberController {

	@Autowired
	private IntMemberMetier metier; 
	
	/* HOME PAGE */
	@RequestMapping(value="/member/index", method = RequestMethod.GET)
	public String index(HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			return "memberindex"; 
		}
	}
	
	//------------------------------------------------------------------
	
	@RequestMapping(value="/member/searchProblem", method = RequestMethod.POST)
	public String searchProblem(Model model, @RequestParam String keyword, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			List<Problem> problems = metier.problemParKeyword(keyword);
			for(Problem p : problems) {
				if(!p.isEnabled()) {
					problems.remove(p);
				}
			}
			model.addAttribute("problems", problems); 
			return "memberproblems"; 
		}
	}
	
	@RequestMapping(value="/member/searchSProblem", method = RequestMethod.POST)
	public String searchSProblem(Model model, @RequestParam String keyword, @RequestParam Long idPr, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			List<SProblem> sproblems = metier.sproblemParKeyword(keyword);
			for(SProblem sp : sproblems) {
				if(!sp.getProblem().getIdPr().equals(idPr) || !sp.isEnabled()){
					sproblems.remove(sp);
				}
			}
			model.addAttribute("problems", sproblems); 
			model.addAttribute("problem", metier.getProblem(idPr)); 
			return "membersproblem"; 
		}
	}
	
	@RequestMapping(value="/member/searchSelfProblem", method = RequestMethod.POST)
	public String searchSelfProblem(Model model, @RequestParam String keyword, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			List<Problem> searchedProblems = metier.problemParKeyword(keyword);
			for(Problem p : searchedProblems) {
				if(!p.getUser().getIdUser().equals(connectedUser.getIdUser()) || !p.isEnabled()){
					searchedProblems.remove(p);
				}
			}
			model.addAttribute("problems", searchedProblems); 
			return "memberselfproblems"; 
		}
	}
	
	//--- Afficher tous les problèmes ---- /member/problems
	@RequestMapping(value="/member/problems", method = RequestMethod.GET)
	public String problems(Model model, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			List<Problem> problems = metier.listproblems();
			for(Problem p : problems) {
				if(!p.isEnabled()) {
					problems.remove(p);
				}
			}
			model.addAttribute("problems", problems); 
			return "memberproblems"; 
		}
	}
	
	//--- Afficher les détails d'un problème ----  afficheProblem
	@RequestMapping(value="/member/afficheProblem", method = RequestMethod.GET)
	public String afficheDetailProblem(Model model, Long idPr, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {	
			Problem p = metier.getProblem(idPr); 
			List<Reply> replies = metier.replyParProblem(p.getIdPr());
			List<Reply> solutions = new ArrayList<Reply>(); 
			List<Reply> others = new ArrayList<Reply>(); 
			for(Reply r : replies) {
				if(r.isEnabled()) {
					if(r.isSolution()) {
						solutions.add(r);
					}else {
						others.add(r);	
					}
				}else {
					replies.remove(r);
				}
			}
			model.addAttribute("pr", p ); 
			model.addAttribute("replies", others);
			model.addAttribute("solutions", solutions);
			model.addAttribute("nbrSolutions", replies.size());
			if(connectedUser.getIdUser().equals(p.getUser().getIdUser())) {
				model.addAttribute("self", true);
			}else {
				model.addAttribute("self", false);
			}
			return "memberproblemdetail"; 
		}	
	}
	
	//------ afficheSProblem
	@RequestMapping(value="/member/afficheSProblem", method = RequestMethod.GET)
	public String afficheSProblem(Model model, Long idPr, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {	
			Problem p = metier.getProblem(idPr);
			List<SProblem> sproblems = metier.sproblemParProb(idPr);
			for(SProblem sp : sproblems) {
				if(!sp.isEnabled()) {
					sproblems.remove(sp);
				}
			}
			model.addAttribute("sproblems", sproblems);
			model.addAttribute("problem", p); 
			return "membersproblem";
		}
	}
	
	//----- addProb
	
	@RequestMapping(value="/member/saveProb", method = RequestMethod.POST)
	public String saveProb(@RequestParam String titlePr, @RequestParam String bodyPr, HttpSession session) {
		
		User user = (User) session.getAttribute("loggedUser"); 
		if(user == null || user.isAdmin()) {
			return "redirect:/login"; 
		}else {
			Problem p = new Problem(); 
			p.setTitlePr(titlePr);
			p.setBodyPr(bodyPr);
			p.setDatePr(new Date());
			p.setEnabled(true);
			p.setUser(user);
			
			metier.ajouterProblem(p);
			return "redirect:/member/problems";
		}
	}
	
	//------- addRepPr


	@RequestMapping(value="/member/saveRepPr", method = RequestMethod.POST)
	public String saveRepPr(@RequestParam String contentRep, @RequestParam Long idPr, HttpSession session) {
		User user = (User) session.getAttribute("loggedUser"); 
		if(user == null || user.isAdmin()) {
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
			return "redirect:/member/afficheProblem?idPr="+problem.getIdPr();
		}
	}
	
	//----------- addSProb
	
	@RequestMapping(value="/member/saveSProb", method = RequestMethod.POST)
	public String saveSProb(@RequestParam Long idPr, @RequestParam String titleSPr, @RequestParam String bodySPr, HttpSession session) {
		User user = (User) session.getAttribute("loggedUser"); 
		if(user == null || user.isAdmin()) {
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
			return "redirect:/member/afficheSProblem?idPr="+idPr;
		}
	}
	
	//------ afficherSProblemRep
	@RequestMapping(value="/member/afficheSProblemRep", method = RequestMethod.GET)
	public String afficheSProblemRep(Model model, Long idSPr, HttpSession session) { 
		User user = (User) session.getAttribute("loggedUser"); 
		if(user == null || user.isAdmin()) {
			return "redirect:/login"; 
		}else {		
			SProblem sp = metier.getSProblem(idSPr); 
			if(user.getIdUser().equals(sp.getUser().getIdUser())) {
				model.addAttribute("self", true);
			}
			List<Reply> replies = metier.replyParSProblem(sp.getIdSPr());
			List<Reply> solutions = new ArrayList<Reply>(); 
			List<Reply> others = new ArrayList<Reply>(); 
			for(Reply r : replies) {
				if(r.isEnabled()) {
					if(r.isSolution()) {
						solutions.add(r);
					}else {
						others.add(r);	
					}
				}else {
					replies.remove(r);
				}
			}
			model.addAttribute("spr", sp ); 
			model.addAttribute("replies", others);
			model.addAttribute("solutions", solutions);
			model.addAttribute("nbrSolutions", replies.size());
			return "membersproblemdetail"; }
	}
	
	//-------- addRepSPr


	@RequestMapping(value="/member/saveRepSPr", method = RequestMethod.POST)
	public String saveRepSPr(@RequestParam String contentRep, @RequestParam Long idPr, @RequestParam Long idSPr, HttpSession session) {
		User user = (User) session.getAttribute("loggedUser"); 
		if(user == null || user.isAdmin()) {
			return "redirect:/login"; 
		}else {	
			Reply rep = new Reply();
			rep.setContentRep(contentRep);
			rep.setDateRep(new Date());
			rep.setSolution(false);
			rep.setEnabled(true);
			rep.setProblem(metier.getProblem(idPr));
			rep.setSproblem(metier.getSProblem(idSPr));
			rep.setUser(user);
			metier.ajouterReply(rep); 
			return "redirect:/member/afficheSProblemRep?idSPr="+idSPr;}
	}	
	
	//----- selfproblems
	@RequestMapping(value="/member/selfproblems", method = RequestMethod.GET)
	public String selfproblems(Model model, HttpSession session) { 
		User connectedUser = (User) session.getAttribute("loggedUser"); 
		if(connectedUser == null || connectedUser.isAdmin()) {
			return "redirect:/login"; 
		}else {
			List<Problem> problems = metier.problemParUser(connectedUser.getIdUser());
			for(Problem p : problems) {
				if(!p.isEnabled()) {
					problems.remove(p);
				}
			}
			model.addAttribute("problems", problems); 
			return "memberselfproblems"; 
		}
	}
	
	//---- selectSolution?idRep
	@RequestMapping(value="/member/selectSolutionProb", method = RequestMethod.GET)
	public String selectSolutionProb(Model model, HttpSession session, @RequestParam Long idRep) { 
		
		User u = (User) session.getAttribute("loggedUser"); 
		if(u == null || u.isAdmin()) {
			return "redirect:/login"; 
		}else {	
			Reply rep = metier.getReply(idRep);
			Problem p = rep.getProblem(); 
			if(p.getUser().getIdUser().equals(u.getIdUser())) {
				rep.setSolution(true);
				metier.modifierReply(rep);
			}		
			return "redirect:/member/afficheProblem?idPr="+p.getIdPr();
		} 
	}
	
	//---- selectSolution?idRep
		@RequestMapping(value="/member/selectSolutionSProb", method = RequestMethod.GET)
		public String selectSolutionSProb(Model model, HttpSession session, @RequestParam Long idRep) { 
			
			User u = (User) session.getAttribute("loggedUser"); 
			if(u == null || u.isAdmin()) {
				return "redirect:/login"; 
			}else {	
				Reply rep = metier.getReply(idRep);
				SProblem sp = rep.getSproblem(); 
				if(sp.getUser().getIdUser().equals(u.getIdUser())) {
					rep.setSolution(true);
					metier.modifierReply(rep);
				}		
				return "redirect:/member/afficheSProblemRep?idSPr="+sp.getIdSPr(); 
			}
		}
	
	//-------Update user
	@RequestMapping(value="/member/updateUser", method = RequestMethod.POST)
	public String updateUser(@RequestParam String firstname, @RequestParam String lastname, @RequestParam String mail, HttpSession session) {
		User u = (User) session.getAttribute("loggedUser"); 
		if(u == null || u.isAdmin()) {
			return "redirect:/login"; 
		}else {	
			u.setFirstName(firstname);
			u.setLastName(lastname);
			u.setMail(mail);
			metier.modifierUser(u);
			return "redirect:/member/index"; 
		}
	}
	
}
