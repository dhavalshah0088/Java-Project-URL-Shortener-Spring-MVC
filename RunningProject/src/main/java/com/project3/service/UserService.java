package com.project3.service;

import com.project3.Model.User;


public interface UserService {
	public User getUser(String username, String password);
	public void insertUser(User user);
	public User getUserID(String username);
	public User checkUser(String username);



}
