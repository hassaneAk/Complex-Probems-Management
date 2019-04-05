package com.ensias.problemsmanagement.metier;

import java.util.List;

import com.ensias.problemsmanagement.entities.User;

public interface IntAuthentificationMetier {
	public List<User> listusers(); 
	public User getUser(Long idUser);
	public User userParLogin(String login); 
	public void addUser(User user); 
}
