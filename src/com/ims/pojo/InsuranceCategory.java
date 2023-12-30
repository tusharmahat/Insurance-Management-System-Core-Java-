package com.ims.pojo;

import java.util.List;
import java.util.ArrayList;

public class InsuranceCategory {
	private final String ID_INI = "200";
	static private int categoryCounts = 0;
	// instance variables
	private String cid;
	private String cname;
	private List<InsuranceSubCategory> subcat = new ArrayList<>();

	// default constructor
	public InsuranceCategory() {
		super();
	}

	// parameterized constructor
	public InsuranceCategory(String cname) {
		super();
		this.cid = ID_INI + categoryCounts;
		this.cname = cname;
		categoryCounts++;
	}

	// getters and setters
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public List<InsuranceSubCategory> getSubcat() {
		return subcat;
	}

	public void setSubcat(List<InsuranceSubCategory> subcat) {
		this.subcat = subcat;
	}
}
