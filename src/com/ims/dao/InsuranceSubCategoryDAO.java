package com.ims.dao;

import java.util.List;

import com.ims.pojo.InsuranceSubCategory;

public interface InsuranceSubCategoryDAO {
	void addSubCategory(String cid); // ADD

	List<InsuranceSubCategory> viewSubCategory(String cid); // VIEW ALL

	void updateSubCategory(String cid, String scid); // UPDATE

	boolean deleteSubCategory(String cid, String scid); // DELETE
}
