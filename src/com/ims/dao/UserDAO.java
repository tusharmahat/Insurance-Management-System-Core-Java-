package com.ims.dao;

import java.util.List;

import com.ims.pojo.User;

public interface UserDAO {

	void addUser();

	String verifyUserNamePword(String uname, String pword);

	String recoverPassword(String uname);
	
	List<User> viewUsers();
	User viewUser(String uname);
}
