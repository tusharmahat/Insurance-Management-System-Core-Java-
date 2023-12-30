package com.ims.dao;

import java.util.List;

import com.ims.pojo.InsuranceCategory;

public interface InsuranceCategoryDAO {
	void addCategory(); // ADD

	InsuranceCategory viewCategory(String cid); // VIEW ONE

	List<InsuranceCategory> viewCategory(); // VIEW ALL

	void updateCategory(String cid); // UPDATE

	boolean deleteCategory(String cid); // DELETE

}
