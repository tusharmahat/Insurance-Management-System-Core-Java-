package com.ims.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ims.dao.UserDAO;
import com.ims.pojo.User;
import com.ims.utility.ValidatorUtilities;

public class UserDAOImpl implements UserDAO {
	// class variables
	private static List<User> users = new ArrayList<>();
	Scanner scnr = new Scanner(System.in);

	

	@Override
	public void addUser() {
		// variables declaration
		String fname;
		String lname;
		String email;
		String uname;
		String pword;
		String role;

		// get details from user for registration
		displayPrompt("First Name:");
		fname = ValidatorUtilities.getStringStartingWithAplha("First Name");
		displayPrompt("Last Name:");
		lname = ValidatorUtilities.getStringStartingWithAplha("First Name");
		displayPrompt("Email:");
		email = scnr.nextLine();
		displayPrompt("User Name:");
		uname = scnr.next();
		displayPrompt("Password:");
		pword = scnr.next();

		// default role client
		role = "customer";

		// create new user using the details provided
		User newUser = new User(fname, lname, email, uname, pword, role);
		// add user to the users list
		while (userExists(newUser)) {
			System.out.println(" User with that username already exists,\n Please try again.");
			displayPrompt("User Name:");
			uname = scnr.next();
			displayPrompt("Password:");
			pword = scnr.next();
			newUser = new User(fname, lname, email, uname, pword, role);
		}

		// add newUser to the list of users
		users.add(newUser);
		System.out.println(" New user registration successful.");
	}

	/**
	 * Checks if the user with same username already exists
	 * 
	 * @param newUser
	 * @return true or false
	 */
	private boolean userExists(User newUser) {
		// go through all users in the system and check if the user with the same
		// username already exists
		for (User u : users) {
			if (u.getUname().equals(newUser.getUname())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String verifyUserNamePword(String uname, String pword) {
		String msg = null;
		// find the user in the users list and check if the username and password matches
		for (User u : users) {
			if (u.getUname().equals(uname)) {
				if (u.getPword().equals(pword)) {
					msg = u.getRole();
					return msg;
				} else {
					msg = "Password wrong";
				}
			}
		}
		return msg;
	}

	@Override
	public String recoverPassword(String uname) {
		// find the user in the users list  and return password
		for (User u : users) {
			if (u.getUname().equals(uname)) {
				return u.getPword();
			}
		}
		return "";
	}

	@Override
	public List<User> viewUsers() {
		//return all users
		return users;
	}

	public User viewUser(String uname) {
		//return a specific user
		for (User u : users) {
			if (u.getUname().equals(uname)) {
				return u;
			}
		}
		return null;
	}

	/**
	 * displays prompt to a
	 * @param type
	 */
	void displayPrompt(String type) {
		System.out.print(" Enter " + type);
	}

}
