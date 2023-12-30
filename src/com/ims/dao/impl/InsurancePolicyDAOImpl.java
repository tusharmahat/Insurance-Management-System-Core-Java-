package com.ims.dao.impl;

import java.util.List;
import java.util.Scanner;
import com.ims.dao.InsurancePolicyDAO;
import com.ims.pojo.InsurancePolicy;
import com.ims.pojo.InsuranceSubCategory;
import com.ims.pojo.User;
import com.ims.utility.ValidatorUtilities;

public class InsurancePolicyDAOImpl implements InsurancePolicyDAO {
	// class variables
	InsuranceSubCategoryDAOImpl iscdi = new InsuranceSubCategoryDAOImpl();
	InsuranceCategoryDAOImpl icdi = new InsuranceCategoryDAOImpl();
	Scanner scnr = new Scanner(System.in);
	UserDAOImpl udi = new UserDAOImpl();

	@Override
	public void addPolicy(String cid, String scid) {
		boolean foundSubCat = false;
		// get all sub-categories inside the selected categories
		List<InsuranceSubCategory> subCategories = icdi.viewCategory(cid).getSubcat();

		// loop though all sub-categories under the selected categories
		for (InsuranceSubCategory subCat : subCategories) {

			// if selected sub-category exists then add new policy
			if (subCat.getSubcid().equals(scid)) {
				// ask for new policy details
				System.out.print(" Enter policy name:");
				String pname = scnr.nextLine();
				System.out.print(" Enter coverage amount:");
				int coverageAmount = ValidatorUtilities.getInteger("coverage amount");

				// create new insurance policy
				InsurancePolicy policy = new InsurancePolicy(pname, coverageAmount);

				List<InsurancePolicy> policies = subCat.getPolicies(); // get list of policies
				policies.add(policy); // add a new policy

				System.out.println(" Insurance Policy added successfully.");
				foundSubCat = true;
			}
		}
		// if not found show error message
		if (!foundSubCat) {
			System.out.println(" Sub-category with SUB_C_ID: " + scid + " does not exist.");
		}

	}

	@Override
	public List<InsurancePolicy> viewPolicy(String cid, String scid) {
		// loop though all sub-categories under selected categories
		for (InsuranceSubCategory subCat : icdi.viewCategory(cid).getSubcat()) {
			// if the sub-category with specific scid exists return it
			if (subCat.getSubcid().equals(scid)) {
				return subCat.getPolicies();
			}
		}

		// if not found show error message
		System.out.println(" Sub-Category with SUB_C_ID " + scid + " does not exist.");
		return null;
	}

	@Override
	public void updatePolicy(String cid, String scid, String pid) {
		boolean foundPolicy = false;
		// loop through all sub-categories inside the selected categories
		for (InsuranceSubCategory isubc : icdi.viewCategory(cid).getSubcat()) {
			// if the selected sub-category exists get all policies
			if (isubc.getSubcid().equals(scid)) {
				// loop through all policy under selected sub-category
				for (InsurancePolicy ip : isubc.getPolicies()) {
					// if selected policy exists update details
					if (ip.getPid().equals(pid)) {
						// ask for new details
						System.out.print(" Enter new policy name:");
						String pname = scnr.nextLine();
						System.out.print(" Enter new coverage amount:");
						int cAmount = ValidatorUtilities.getInteger("coverage amount");
						// set new details
						ip.setPname(pname);
						ip.setCoverageAmount(cAmount);

						System.out.println(" Insurance policy updated successfully.");
						foundPolicy = true;
					}
				}
			}
		}
		// if not found show error msg
		if (!foundPolicy) {
			System.out.println(" Policy with P_ID: " + pid + " does not exist.");
		}
	}

	@Override
	public boolean deletePolicy(String cid, String scid, String pid) {
		// loop thoough all sub-categories inside the selected categories
		for (InsuranceSubCategory isubc : icdi.viewCategory(cid).getSubcat()) {
			if (isubc.getSubcid().equals(scid)) {
				List<InsurancePolicy> inPolicies = isubc.getPolicies();

				// if the policy exists delete it
				if (inPolicies.removeIf(ip -> ip.getPid().equals(pid))) {
					System.out.println(" Insurance policy updated successfully.");
					return true;
				}
			}
		}
		// if not found show error
		System.out.println(" Policy with P_ID: " + pid + " does not exist.");
		return false;
	}

	/**
	 * apply selected policy under selected sub-cat under selected cat for user with specific uname
	 * @param uname
	 * @param cid
	 * @param scid
	 * @param pid
	 */
	public void applyPolicy(String uname, String cid, String scid, String pid) {
		boolean foundPolicy = false;
		User user = udi.viewUser(uname);

		// loop through all sub-categories inside the selected categories
		for (InsuranceSubCategory isubc : icdi.viewCategory(cid).getSubcat()) {

			// if the sub-category is found then get all policies
			if (isubc.getSubcid().equals(scid)) {
				// loop though all policies under selected sub-categories
				for (InsurancePolicy ip : isubc.getPolicies()) {
					// if policy is found the apply for policy
					if (ip.getPid().equals(pid)) {
						// create a new policy with the applicant as holder
						InsurancePolicy newPolicy = new InsurancePolicy(ip.getPname(), ip.getCoverageAmount());
						newPolicy.setPid(pid);
						newPolicy.setHolder(user);

						// get users policies and add new policy
						List<InsurancePolicy> userPolicies = user.getPolicy();
						userPolicies.add(newPolicy);// add new policies

						System.out.println(" Insurance policy applied successfully.");
						foundPolicy = true;
					}
				}
			}
		}
		// if policy not found show error msg
		if (!foundPolicy) {
			System.out.println(" Policy with P_ID: " + pid + " does not exist.");
		}
	}

	/**
	 * Respond to selected policy application of selected user
	 * 
	 * @param userName
	 * @param pid
	 */
	public void respondRequest(String userName, String pid) {
		List<User> users = udi.viewUsers();
		boolean foundUserName = false;
		boolean foundPolicy = false;

		// got though all users
		for (User user : users) {
			// if user is found
			if (user.getUname().equals(userName)) {
				foundUserName = true;
				// go though all the user policy
				for (InsurancePolicy ip : user.getPolicy()) {
					// if the selected policy is found, ask admin what to do with it
					if (ip.getPid().equals(pid)) {
						foundPolicy = true;
						System.out.print(" Select your response\n 1)Approve\t2)Disapprove\n Your response: ");
						switch (ValidatorUtilities.getInteger("choice")) {
						case 1:
							ip.setActive(true);
							System.out.print(" Your approved the policy ID: " + pid + " of customer with username "
									+ userName + "\n");
							break;
						case 2:
							ip.setActive(false);
							System.out.print(" Your disapproved the policy ID: " + pid + " of customer with username "
									+ userName + "\n");
							break;
						default:
							System.out.println("Please choose 1 or 2");
						}

					}
				}
			}
		}

		// if user not found show error msg
		if (!foundUserName) {
			System.out.println(" User with User Name: " + userName + " does not exist.");
		}

		// if policy not found show error msg
		if (foundUserName && !foundPolicy) {
			System.out.println(" Policy with P_ID: " + pid + " does not exist.");
		}

	}
}
