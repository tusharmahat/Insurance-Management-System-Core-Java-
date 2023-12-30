package com.ims.client;

import java.util.List;
import java.util.Scanner;

import com.ims.dao.impl.InsurancePolicyDAOImpl;
import com.ims.dao.impl.InsuranceSubCategoryDAOImpl;
import com.ims.pojo.InsurancePolicy;
import com.ims.utility.PrintUtilities;
import com.ims.utility.ValidatorUtilities;

public class PolicyMenu {
	// static variables of the class
	static Scanner scnr = new Scanner(System.in);
	static InsurancePolicyDAOImpl ipdi = new InsurancePolicyDAOImpl();
	static InsuranceSubCategoryDAOImpl iscdi = new InsuranceSubCategoryDAOImpl();

	/**
	 * displays the menu option of policy using helper methods in Utilities class
	 */
	public static void displayMenu() {
		PrintUtilities.displayTitle("POLICY MENu");
		PrintUtilities.printBlankLines(3);
		PrintUtilities.displayChoice("1) ADD POLICY");
		PrintUtilities.displayChoice("2) VIEW ALL POLICY");
		PrintUtilities.displayChoice("3) UPDATE POLICY");
		PrintUtilities.displayChoice("4) DELETE POLICY");
		PrintUtilities.displayChoice("5) Back");
		PrintUtilities.displayEndLine();
		PrintUtilities.displayChoicePrompt();
	}

	/**
	 * displays policy dashboard emnu
	 */
	static void displayPolicyDashboard() {
		// local variables
		String cid;
		String scid;
		String ipid;

		while (true) {// start while
			displayMenu();// displays menu
			switch (ValidatorUtilities.getInteger("choice")) {
			case 1:
				// if there are any categories, asks user to select a category to add
				// sub-categories into
				if (CategoryMenu.displayCategories()) {
					System.out.print(
							" Select a category id to add policy into:");
					cid = scnr.next();
					// if there are any sub-categories, asks user to select a sub-category to add
					// policy into
					if (SubCategoryMenu.displayAllSubCategories(cid)) {
						System.out.print(
								" Select a sub-category id to add policy into:");
						scid = scnr.next();

						// adds policy
						ipdi.addPolicy(cid, scid);
					}
				}
				PrintUtilities.pause();
				break;
			case 2:
				// if there are any categories, asks user to select a category to view
				// sub-categories
				if (CategoryMenu.displayCategories()) {
					System.out.print(
							" Select a category id to view policy from:");
					cid = scnr.next();
					// if there are any sub-categories, asks user to select a sub-category to view
					// policies
					if (SubCategoryMenu.displayAllSubCategories(cid)) {
						System.out.print(
								" Select a sub-category id to view policy from:");
						scid = scnr.next();

						// displays policy
						displayAllPolicies(cid, scid);
					}
				}
				PrintUtilities.pause();
				break;
			case 3:
				// if there are any categories, asks user to select a category to update
				// policies
				if (CategoryMenu.displayCategories()) {
					System.out
							.print(" Select a category id to update policy:");
					cid = scnr.next();
					// if there are any sub-categories, asks user to select a sub-category to update
					// policies
					if (SubCategoryMenu.displayAllSubCategories(cid)) {
						System.out.print(
								" Select a sub-category id to update policy:");
						scid = scnr.next();
						// if there are any policies, asks user to select a policy to update it
						if (displayAllPolicies(cid, scid)) {
							System.out.print(
									" Select a policy id to update policy:");
							ipid = scnr.next();

							// updates policy
							ipdi.updatePolicy(cid, scid, ipid);
						}
					}
				}
				PrintUtilities.pause();
				break;
			case 4:
				// if there are any categories, asks user to select a category to delete
				// policies
				if (CategoryMenu.displayCategories()) {
					System.out.print(
							" Select a category id to delete policy from:");
					cid = scnr.next();
					// if there are any sub-categories, asks user to select a sub-category to delete
					// policies
					if (SubCategoryMenu.displayAllSubCategories(cid)) {
						System.out.print(
								" Select a sub-category id to delete policy from:");
						scid = scnr.next();
						// if there are any policies, asks user to select a policy to delete it
						if (displayAllPolicies(cid, scid)) {
							System.out.print(
									" Select a policy id to delete policy:");
							ipid = scnr.next();

							// deletes policy
							ipdi.deletePolicy(cid, scid, ipid);
						}
					}
				}
				PrintUtilities.pause();
				break;
			case 5:
				// go back to admin dashboard menu
				AdminDashboard.displayAdminDashboard();
				break;
			default:
				System.out.println(" Please enter value between 1-5.");
			}
		} // end while
	}

	/**
	 * displays all the policies in the selected category in the selected
	 * sub-categories and return true if policies found else returns false
	 * 
	 * @param cid,  category id
	 * @param scid, sub-category id
	 * @return true if found, else false
	 */
	public static boolean displayAllPolicies(String cid, String scid) {
		// list of policies
		List<InsurancePolicy> policies = ipdi.viewPolicy(cid, scid);

		// if policies is not null displays them, else shows messsage none found
		if (policies != null && policies.size() != 0) {// start if
			PrintUtilities
					.displayListTitle("POLICIES UNDER SELECTED SUB-CATEGORY");
			// loops through all each policy and displays them
			for (InsurancePolicy p : policies) {
				PrintUtilities.displayListItem("P_ID: " + p.getPid());
				PrintUtilities.displayListItem("Policy Name: " + p.getPname());
				PrintUtilities.displayListItem(
						"Coverage Amount: $" + p.getCoverageAmount());
				PrintUtilities.displayDividerLine();
			}
			return true;
		} else {
			System.out.println(
					" No insurance policies found in the sub-category with SUB_C_ID: "
							+ scid);
			return false;
		} // end of
	}
}
