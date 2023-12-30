package com.ims.pojo;

import java.util.ArrayList;
import java.util.List;

public class InsuranceSubCategory {
	private final String ID_INI = "300";
	static private int subCategoryCounts = 0;
	// instance variables
	private String subcid;
	private String subcname;
	private List<InsurancePolicy> policies = new ArrayList<>();

	// default constructor
	public InsuranceSubCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	// parameterized constructor
	public InsuranceSubCategory(String subcname) {
		super();
		this.subcid = ID_INI + subCategoryCounts;
		this.subcname = subcname;
		subCategoryCounts++;
	}

	// getters and setters
	public String getSubcid() {
		return subcid;
	}

	public void setSubcid(String subcid) {
		this.subcid = subcid;
	}

	public String getSubcname() {
		return subcname;
	}

	public void setSubcname(String subcname) {
		this.subcname = subcname;
	}

	public List<InsurancePolicy> getPolicies() {
		return policies;
	}

	public void setPolicies(List<InsurancePolicy> policies) {
		this.policies = policies;
	}

}
