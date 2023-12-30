package com.ims.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ims.dao.InsuranceCategoryDAO;
import com.ims.pojo.InsuranceCategory;

public class InsuranceCategoryDAOImpl implements InsuranceCategoryDAO {
	// class data members
	private static List<InsuranceCategory> insuranceCategory = new ArrayList<>();
	static Scanner scnr = new Scanner(System.in);

	@Override
	public void addCategory() {
		// ask for category name to the user
		System.out.print(" Enter category name:");
		String cname = scnr.nextLine();

		// create a new category and add to category lists
		InsuranceCategory category = new InsuranceCategory(cname);
		insuranceCategory.add(category);
		System.out.println(" Insurance category added successfully.");
	}

	@Override
	public InsuranceCategory viewCategory(String cid) {
		// go though all the category and return the category with the specific cid
		for (InsuranceCategory ic : insuranceCategory) {
			if (ic.getCid().equals(cid)) {
				return ic;
			}
		}
		System.out.println(" Category with C_ID: " + cid + " does not exist.");
		return null;
	}

	@Override
	public List<InsuranceCategory> viewCategory() {
		// return the category list
		return insuranceCategory;
	}

	@Override
	public void updateCategory(String cid) {
		boolean foundCategory = false;
		// loop through all the category and update it if found
		for (InsuranceCategory ic : insuranceCategory) {
			// check if the category exists
			if (ic.getCid().equals(cid)) {
				foundCategory = true;
				// ask for updated details from user
				System.out.print(" Enter new category name:");
				String cname = scnr.nextLine();

				// update details
				ic.setCname(cname);
				System.out.println(" Insurance category name updated successfully.");
			}
		}

		// if not found show error message
		if (!foundCategory) {
			System.out.println(" Category with C_ID: " + cid + " does not exist.");
		}
	}

	@Override
	public boolean deleteCategory(String cid) {
		// remove the category if it exists
		if (insuranceCategory.removeIf(ic -> ic.getCid().equals(cid))) {
			System.out.println(" Insurance category with id = " + cid + " deleted successfully.");
			return true;
		}
		// if not show error message and return false
		System.out.println(" Category with C_ID: " + cid + " does not exist.");
		return false;
	}
}
