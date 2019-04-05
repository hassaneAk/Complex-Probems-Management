package com.ensias.problemsmanagement.controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ensias.problemsmanagement.entities.User;
import com.ensias.problemsmanagement.metier.IntAuthentificationMetier;

@Controller
public class LoginController {
	
	@Autowired
	private IntAuthentificationMetier metier; 
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model, HttpSession session) {
		session.setAttribute("loggedUser", null);
		model.addAttribute("user", new User()); 
		return "login";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register(Model model, HttpSession session) {
		session.setAttribute("loggedUser", null);
		model.addAttribute("user", new User()); 
		return "registration";
	}
	
	
	@RequestMapping(value="/checkUser", method=RequestMethod.POST)
	public String checkUser(@RequestParam String login, @RequestParam String mdp, ModelMap model, HttpSession session) {
		
		//Hash the password
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(mdp.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            mdp = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		List<User> users = metier.listusers(); 
		for(User u : users) {
			if(u.getLogin().equals(login) && u.isEnabled()) {
				if(u.getMdp().equals(mdp)) {
					session.setAttribute("loggedUser", u); 
					if(u.isAdmin()) {
						return "redirect:/admin/index";
					}else {
						return "redirect:/member/index"; 
					}
				}else {
					model.addAttribute("mdpError", "Wrong password !");
					return "login";
				}
			}
		}
		model.addAttribute("loginError", "Username not found !");
		return "login";
	}
	
	
	@RequestMapping(value="/saveUser", method=RequestMethod.POST)
	public String saveUser(@RequestParam String firstname,
			@RequestParam String lastname, 
			@RequestParam String mail,
			@RequestParam String login,
			@RequestParam String mdp,
			@RequestParam String cmdp,	ModelMap model) {
		
		if(mdp.equals(cmdp)) {
			List<User> users = metier.listusers(); 
			for(User u : users) {
				if(u.getFirstName().equals(firstname) && u.getLastName().equals(lastname)) {
					model.addAttribute("userExistError", "User with the same last name & first name already exists !"); 
					return "login"; 
				}else if(u.getLogin().equals(login)){
					model.addAttribute("loginExistError", "Login already in use !"); 
					return "login"; 
				}
			}
			
			//Hash the password
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(mdp.getBytes());
				byte[] bytes = md.digest();
				StringBuilder sb = new StringBuilder();
	            for(int i=0; i< bytes.length ;i++)
	            {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            //Get complete hashed password in hex format
	            mdp = sb.toString();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			User u = new User(firstname, lastname, mail, login, mdp);
			u.setAdmin(false);
			u.setEnabled(true);
			
			metier.addUser(u); 
			return "registration"; 
			
		}else {
			model.addAttribute("mdpMatchError", "Yours passwords does not match !"); 
			return "registration"; 
		}
	}
	
}
