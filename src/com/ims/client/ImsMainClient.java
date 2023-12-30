package com.ims.client;

import java.util.Scanner;
import com.ims.dao.impl.UserDAOImpl;
import com.ims.utility.DataLoader;
import com.ims.utility.PrintUtilities;
import com.ims.utility.ValidatorUtilities;
/**
 * Main class of the program
 */
public class ImsMainClient extends AdminDashboard {
	// static variables of the program
	static Scanner scnr = new Scanner(System.in);
	private static UserDAOImpl udi = new UserDAOImpl();

	/**
	 * main method of the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// register one admin user when the app starts
		DataLoader.load();

		// display Insurance Management System Dashboard
		displayImsClientDashBoard();
	}

	static void displayImsClientDashBoard() {
		while (true) {//start while
			// display main dashboard menu
			displayMenu();

			switch (ValidatorUtilities.getInteger("choice")) { //while
			case 1:
				// add a new user
				udi.addUser();
				PrintUtilities.pause(); //pause
				break;
			case 2:
				// verify and login
				login(udi);
				PrintUtilities.pause(); //pause
				break;
			case 3:
				// ask for uname
				System.out.print(" User Name:");
				String username = scnr.next();
				
				String msg = udi.recoverPassword(username);
				
				//check if the password is returned
				if (!msg.equals("")) {
					System.out.println(" Your password is " + msg);
				} else {
					System.out.println(" No such user found.");
				}
				
				PrintUtilities.pause(); //pause
				break;
			case 4:
				// if there are any categories display them
				if (CategoryMenu.displayCategories()) {
					System.out.print(
							" Select a category id to view sub-category from:");
					String cid = scnr.next();
					// if there are any sub-categories, asks user to select a sub-category to view
					// policies
					if (SubCategoryMenu.displayAllSubCategories(cid)) {
						System.out.print(
								" Select a sub-category id to view policy from:");
						String scid = scnr.next();

						// displays policy if there are any
						PolicyMenu.displayAllPolicies(cid, scid);
					}
				} 
				PrintUtilities.pause(); //pause
				break;
			case 5:
				System.out.println(" Thank you for using this application");
				System.exit(0);
				break;
			default:
				System.out.println(" Please enter value between 1-5.");
			}
		}
	}

	static void login(UserDAOImpl udi) {
		// ask for uname and password from user
		System.out.print(" User Name:");
		String uname = scnr.next();
		System.out.print(" Pasword  :");
		String pword = scnr.next();

		// verify uname and password from the registered users
		String msg = udi.verifyUserNamePword(uname, pword);
		/*
		 * if msg is not null try showing menu, else show message that the user is not
		 * found
		 */
		if (msg != null) {
			/*
			 * if verifyUserNamePword() returns "admin", show admin dashboard; if it returns
			 * "customer", show client dashboard; if it returns something else display the
			 * message, "password wrong"
			 */
			if (msg.equals("admin")) {
				AdminDashboard.displayAdminDashboard();
			} else if (msg.equals("customer")) {
				ClientDashBoard.displayClientDashboard(uname);
			} else {
				System.out.println(" " + msg);
			}
		} else {
			System.out.println(" No such user found.");
		}
	}

	/**
	 * displays menu options using the helper methods from Utilities class
	 */
	public static void displayMenu() {
		PrintUtilities.displayTitle("INSURANCE APP");
		PrintUtilities.printBlankLines(3);
		PrintUtilities.displayChoice("1) Register");
		PrintUtilities.displayChoice("2) Login");
		PrintUtilities.displayChoice("3) Forgot Password?");
		PrintUtilities.displayChoice("4) View Insurance Categories");
		PrintUtilities.displayChoice("5) Exit");
		PrintUtilities.displayEndLine();
		PrintUtilities.displayChoicePrompt();
	}

}
