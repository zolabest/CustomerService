package com.complaint.service;



import java.util.ArrayList;

import com.complaint.dao.UserDAO;
import com.complaint.dao.UserList;
import com.complaint.models.User;

public class UserService {
	private UserDAO dao;
	
	public UserService()
	{
		dao = new UserDAO();
	}
	public int save(User user) {
		return dao.save(user);
	}
	public User login (String username, String password)
	{
		User user = dao.login(username, password);
		return user;
	}
	public ArrayList<User>retrieve(){
		return dao.retrieve();
	}
	public UserList engineers(String pin){
		return dao.engineers(pin);
	}
	//
	public User retrieve (String username)
	{
		User user = dao.retrieve(username);
		return user;
	}
	public User retrieve (int id)
	{
		User user = dao.retrieve(id);
		return user;
	}
	public int delete (int id)
	{
		return dao.delete(id);
	}
}
