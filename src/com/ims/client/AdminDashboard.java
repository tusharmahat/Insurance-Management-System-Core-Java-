package com.ims.client;

import java.util.List;
import java.util.Scanner;
import com.ims.dao.impl.InsurancePolicyDAOImpl;
import com.ims.dao.impl.UserDAOImpl;
import com.ims.pojo.InsurancePolicy;
import com.ims.pojo.User;
import com.ims.utility.PrintUtilities;
import com.ims.utility.ValidatorUtilities;

public class AdminDashboard {
	// static variable
	static Scanner scnr = new Scanner(System.in);

	/**
	 * displays menu options using helper methods in Utilities class
	 */
	public static void displayMenu() {
		PrintUtilities.displayTitle("WELCOME TO ADMIN DASHBOARD");
		PrintUtilities.printBlankLines(3);
		PrintUtilities.displayChoice("1) View User List");
		PrintUtilities.displayChoice("2) Category Menu");
		PrintUtilities.displayChoice("3) Sub-Category Menu");
		PrintUtilities.displayChoice("4) Policy Menu");
		PrintUtilities.displayChoice("5) Manage Policy Request/s");
		PrintUtilities.displayChoice("6) Add a new user");
		PrintUtilities.displayChoice("7) Back");
		PrintUtilities.displayEndLine();
		PrintUtilities.displayChoicePrompt();
	}

	/**
	 * displays admin dashboard if the admin logs in
	 */
	static void displayAdminDashboard() {
		// user Data Access Object
		UserDAOImpl udi = new UserDAOImpl();

		// policy Data Access Object
		InsurancePolicyDAOImpl ipdi = new InsurancePolicyDAOImpl();

		// while loop start
		while (true) {
			displayMenu();
			switch (ValidatorUtilities.getInteger("choice")) {
			case 1:
				// show all the users
				displayAllUsers(udi);
				PrintUtilities.pause();
				break;
			case 2:
				// show category menu
				CategoryMenu.displayCategoryDashboard();
				break;
			case 3:
				// show sub-category menu
				SubCategoryMenu.displaySubCategoryDashboard();
				break;
			case 4:
				// show policy menu
				PolicyMenu.displayPolicyDashboard();
				break;
			case 5:
				// show the list of policy requests
				managePolicyRequest(udi, ipdi);
				PrintUtilities.pause();
				break;
			case 6:
				// add a new user
				udi.addUser();
				break;
			case 7:
				// go back to Insurance Management System Dashboard
				System.out.println(" Logged out successfully");
				PrintUtilities.pause();
				ImsMainClient.displayImsClientDashBoard();
				break;
			default:
				System.out.println(" Please enter value between 1-5.");
			}
		} // end of while loop

	}

	/**
	 * Shows the list of policy applied by all the users, and asks the admin to
	 * choose the username and policy id to respond the request, the admin can
	 * either accept the policy or decline
	 * 
	 * @param udi,  User Data Access Object
	 * @param ipdi, Insurance Policy Data Access Object
	 */
	private static void managePolicyRequest(UserDAOImpl udi,
			InsurancePolicyDAOImpl ipdi) {
		// local variable
		boolean foundPolicy = false;
		// list of all users
		List<User> users = udi.viewUsers();

		/*
		 * if users exist then go through all users and show the policies applied by
		 * them
		 */
		if (users.size() != 0) { // start of outer if-else
			// go though all users
			for (User u : users) {
				// get list of policies that the user apply
				List<InsurancePolicy> userPolicies = u.getPolicy();
				// go through all policies and print details
				for (InsurancePolicy ip : userPolicies) {
					if (!foundPolicy) {
						PrintUtilities.displayListTitle("POLICY REQUESTS");
					}
					PrintUtilities.displayListItem("P_ID:" + ip.getPid());
					PrintUtilities
							.displayListItem("Policy Name:" + ip.getPname());
					PrintUtilities.displayListItem(
							"Owner User Name:" + ip.getHolder().getUname());
					PrintUtilities.displayListItem(
							"Coverage Amount:$" + ip.getCoverageAmount());
					PrintUtilities.displayListItem("Status:" + ip.isActive());
					PrintUtilities.displayDividerLine();
					foundPolicy = true; // found policies
				}
			}
			// if policies found, ask admin which user's which policy he want to respond to
			if (foundPolicy) { // start inner if-else
				System.out.print(" Select a username to respond the request:");
				String userName = scnr.next();
				System.out
						.print(" Select a policy id to respond the request:");
				String pid = scnr.next();

				// respond to the request; accept or decline
				ipdi.respondRequest(userName, pid);
			} else {
				// no policies requests found
				System.out.println(" There are no policy requests.");
			} // end of inner if-else
		} else {
			// no users registered in the system
			System.out.println(" No users registered.");
		} // end of outer if-else

	}

	/**
	 * displays all users registered in the system using helper function in
	 * Utilities class
	 * 
	 * @param udi User Data Access Object
	 */
	static void displayAllUsers(UserDAOImpl udi) {
		List<User> users = udi.viewUsers();
		PrintUtilities.displayListTitle("All Registered Users");
		for (User u : users) {
			PrintUtilities.displayListItem("ID:" + u.getId());
			PrintUtilities.displayListItem("First Name:" + u.getFname());
			PrintUtilities.displayListItem("Last Name:" + u.getLname());
			PrintUtilities.displayListItem("Email:" + u.getEmail());
			PrintUtilities.displayListItem("User Name:" + u.getUname());
			PrintUtilities.displayDividerLine();
		}
	}
}
