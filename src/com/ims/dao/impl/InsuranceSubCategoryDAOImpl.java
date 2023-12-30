package com.ims.dao.impl;

import java.util.List;
import java.util.Scanner;

import com.ims.dao.InsuranceSubCategoryDAO;
import com.ims.pojo.InsuranceCategory;
import com.ims.pojo.InsuranceSubCategory;

public class InsuranceSubCategoryDAOImpl implements InsuranceSubCategoryDAO {
	// class variables
	static Scanner scnr = new Scanner(System.in);
	InsuranceCategoryDAOImpl icdi = new InsuranceCategoryDAOImpl();

	@Override
	public void addSubCategory(String cid) {
		// get the specific category that the user wants to add sub-category into
		InsuranceCategory ic = icdi.viewCategory(cid);

		// if the category is found, add sub-category
		if (ic != null) {
			// ask for sub-category name
			System.out.print(" Enter sub-category name:");
			String subcname = scnr.nextLine();

			// get all sub-categories inside the selected categories
			List<InsuranceSubCategory> subCategories = ic.getSubcat();

			// add a new sub-category
			subCategories.add(new InsuranceSubCategory(subcname));

			System.out.println(" Insurance Sub-category added successfully.");
		}
	}

	@Override
	public List<InsuranceSubCategory> viewSubCategory(String cid) {
		// get all sub-categories inside the selected categories
		InsuranceCategory ic = icdi.viewCategory(cid);
		if (ic != null) {
			return ic.getSubcat();
		}
		return null;
	}

	@Override
	public void updateSubCategory(String cid, String scid) {
		boolean foundSubCategory = false;
		// loop through all sub-categories inside the selected categories and find the
		// sub-category with specific scid to update the details
		for (InsuranceSubCategory isubc : icdi.viewCategory(cid).getSubcat()) {
			// if found update
			if (isubc.getSubcid().equals(scid)) {
				foundSubCategory = true;
				// ask new details
				System.out.print(" Enter new sub-category name:");
				String subcname = scnr.nextLine();

				// update details
				isubc.setSubcname(subcname);
				System.out.println(" Insurance sub-category name updated successfully.");
			}
		}

		// if not found show error
		if (!foundSubCategory) {
			System.out.println(" Sub-category with SUB_C_ID: " + scid + " does not exist.");
		}
	}

	@Override
	public boolean deleteSubCategory(String cid, String scid) {
		// get all the sub-category in the selected category
		List<InsuranceSubCategory> inscats = icdi.viewCategory(cid).getSubcat();

		// remove if the sub-category with specific scid is found
		if (inscats.removeIf(insc -> insc.getSubcid().equals(scid))) {
			System.out.println(" Insurance Sub-category with id = " + scid + " deleted successfully.");
			return true;
		}

		// if not found show error
		System.out.println(" Sub-category with SUB_C_ID: " + scid + " does not exist.");
		return false;
	}

}
