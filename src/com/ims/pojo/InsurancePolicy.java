package com.ims.pojo;

public class InsurancePolicy {
	private final String ID_INI = "400";
	static private int policyCounts = 0;
	// instance variables
	private String pid;
	private String pname;
	private User holder;
	private int coverageAmount;
	private boolean isActive;

	// default constructor
	public InsurancePolicy() {
		super();
	}

	// parameterized constructor
	public InsurancePolicy(String pname, int coverageAmount) {
		super();
		this.pid = ID_INI + policyCounts;
		this.pname = pname;
		this.coverageAmount = coverageAmount;
		policyCounts++;
	}

	// getters and setters
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getCoverageAmount() {
		return coverageAmount;
	}

	public void setCoverageAmount(int coverageAmount) {
		this.coverageAmount = coverageAmount;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public User getHolder() {
		return holder;
	}

	public void setHolder(User holder) {
		this.holder = holder;
	}
}
