package com.ims.pojo;

import java.util.ArrayList;
import java.util.List;

public class User {
	private final String ID_INI = "100";
	static private int userCounts = 0;
	// instance variables
	private String id;
	private String fname;
	private String lname;
	private String email;
	private String uname;
	private String pword;
	private String role;
	private List<InsurancePolicy> policy = new ArrayList<>();

	// default constructor
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	// parameterized constructor
	public User(String fname, String lname, String email, String uname,
			String pword, String role) {
		super();
		this.id = ID_INI + userCounts;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.uname = uname;
		this.pword = pword;
		this.role = role;
		userCounts++;
	}

	// getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPword() {
		return pword;
	}

	public void setPword(String pword) {
		this.pword = pword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<InsurancePolicy> getPolicy() {
		return policy;
	}

	public void setPolicy(List<InsurancePolicy> policy) {
		this.policy = policy;
	}
}
