package com.ims.client;

import java.util.List;
import java.util.Scanner;

import com.ims.dao.impl.InsurancePolicyDAOImpl;
import com.ims.dao.impl.UserDAOImpl;
import com.ims.pojo.InsurancePolicy;
import com.ims.utility.PrintUtilities;
import com.ims.utility.ValidatorUtilities;

public class ClientDashBoard {
	// static variable
	static Scanner scnr = new Scanner(System.in);

	/**
	 * displays menu options using helper method using Utilities class
	 */
	public static void displayMenu() {
		PrintUtilities.displayTitle("WELCOME TO CLIENT DASHBOARD");
		PrintUtilities.printBlankLines(3);
		PrintUtilities.displayChoice("1) View All Categories");
		PrintUtilities.displayChoice("2) View All Sub-Categories");
		PrintUtilities.displayChoice("3) View Policies");
		PrintUtilities.displayChoice("4) Apply for Policy");
		PrintUtilities.displayChoice("5) View policies that you hold");
		PrintUtilities.displayChoice("6) Back");
		PrintUtilities.displayEndLine();
		PrintUtilities.displayChoicePrompt();
	}

	/**
	 * views the list of policies that the user holds
	 * 
	 * @param uname, User Name of the loggedin user
	 */
	private static void viewHoldingPolicies(String uname) {
		// list of policies that the user holds
		List<InsurancePolicy> userPolicies = new UserDAOImpl().viewUser(uname)
				.getPolicy();
		// local variable to count the number of policies
		int userHoldPolicyCount = 0;

		// loop though the list of all the policies that the user holds
		for (InsurancePolicy ip : userPolicies) {
			// show policy if it is active
			if (ip.isActive()) {// start if
				if (userHoldPolicyCount < 1) {
					PrintUtilities.displayListTitle("POLICIES ON YOUR HOLD");
				}
				PrintUtilities.displayListItem("P_ID:" + ip.getPid());
				PrintUtilities.displayListItem("Policy Name:" + ip.getPname());
				PrintUtilities.displayListItem(
						"Owner User Name:" + ip.getHolder().getUname());
				PrintUtilities.displayListItem(
						"Coverage Amount:$" + ip.getCoverageAmount());
				PrintUtilities.displayDividerLine();
				userHoldPolicyCount++;
			} // end if
		}

		// if the user has no policy in hold, show message
		if (userHoldPolicyCount == 0) {// start if
			System.out.println(
					" You hold no policies.\n Please apply for policies and wait for it's approval.");
		} // end if

	}

	/**
	 * display client dashboard if the loggedin user is a customer
	 * 
	 * @param uname, User Name of the loggedin User
	 */
	static void displayClientDashboard(String uname) {
		// Policy Data Access Object
		InsurancePolicyDAOImpl ipdi = new InsurancePolicyDAOImpl();
		// local variable
		String cid;
		String scid;
		String pid;

		while (true) {// start of while
			displayMenu(); // show Menu
			switch (ValidatorUtilities.getInteger("choice")) {
			case 1:
				CategoryMenu.displayCategories(); // show Categories
				PrintUtilities.pause();
				break;
			case 2:
				// if there are any categories, ask user to select a category to display
				// sub-categories
				if (CategoryMenu.displayCategories()) {// start of if
					System.out.print(
							" Select a category id to view sub-categories:");
					cid = scnr.next();
					// show sub-categories of selected category
					SubCategoryMenu.displayAllSubCategories(cid);
				} // end of if

				PrintUtilities.pause();
				break;
			case 3:
				// if there are any categories, ask user to select a category to display
				// sub-categories
				if (CategoryMenu.displayCategories()) {// start of outer if
					System.out.print(
							" Select a category id to view policy from:");
					cid = scnr.next();

					// if there are any sub-categories, ask user to select a sub-category to display
					// policy
					if (SubCategoryMenu.displayAllSubCategories(cid)) {// start inner if
						System.out.print(
								" Select a sub-category id to view policy from:");
						scid = scnr.next();
						// display policy
						PolicyMenu.displayAllPolicies(cid, scid);
					} // end inner if
				} // end of outer if

				PrintUtilities.pause();
				break;
			case 4:
				// if there are any categories, ask user to select a category to display
				// sub-categories
				if (CategoryMenu.displayCategories()) {// start outer if
					System.out.print(" Select a category id to apply policy:");
					cid = scnr.next();
					// if there are any sub-categories, ask user to select a sub-category to display
					// policy
					if (SubCategoryMenu.displayAllSubCategories(cid)) {// start inner if
						System.out.print(
								" Select a sub-category id to apply policy:");
						scid = scnr.next();
						// if there are any policies, ask user to select a policy to apply
						// policy
						if (PolicyMenu.displayAllPolicies(cid, scid)) {// start innermost if
							System.out.print(
									" Select a policy id to apply policy:");
							pid = scnr.next();

							// apply the selected policy
							ipdi.applyPolicy(uname, cid, scid, pid);
						} // end innermost if
					} // end inner if
				} // end outer if
				PrintUtilities.pause();
				break;
			case 5:
				// view the policies that the user holds
				viewHoldingPolicies(uname);
				PrintUtilities.pause();
				break;
			case 6:
				System.out.println(" Logged out successfully");
				PrintUtilities.pause();
				// go back to Insurance System Management Dashboard
				ImsMainClient.displayImsClientDashBoard();
				break;
			default:
				System.out.println(" Please enter value between 1-5.");
			}
		} // end of while
	}
}
