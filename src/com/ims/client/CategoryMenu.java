package com.ims.client;

import java.util.List;
import java.util.Scanner;

import com.ims.dao.impl.InsuranceCategoryDAOImpl;
import com.ims.pojo.InsuranceCategory;
import com.ims.utility.PrintUtilities;
import com.ims.utility.ValidatorUtilities;

public class CategoryMenu {
	// static variable of the class
	static Scanner scnr = new Scanner(System.in);
	// static category Data Access Object
	static InsuranceCategoryDAOImpl icdi = new InsuranceCategoryDAOImpl();

	/**
	 * displays menu options using helper methods in the Utilities class
	 */
	public static void displayMenu() {
		PrintUtilities.displayTitle("CATEGORY MENU");
		PrintUtilities.printBlankLines(3);
		PrintUtilities.displayChoice("1) ADD CATEGORY");
		PrintUtilities.displayChoice("2) VIEW ALL CATEGORY");
		PrintUtilities.displayChoice("3) UPDATE CATEGORY");
		PrintUtilities.displayChoice("4) DELETE CATEGORY");
		PrintUtilities.displayChoice("5) Back");
		PrintUtilities.displayEndLine();
		PrintUtilities.displayChoicePrompt();
	}

	/**
	 * display category dashboard menu if category menu is selected
	 */
	static void displayCategoryDashboard() {

		while (true) {// start while
			// local variable
			String cid;

			displayMenu();// show menu
			switch (ValidatorUtilities.getInteger("choice")) {
			case 1:
				icdi.addCategory();// add category
				PrintUtilities.pause();
				break;
			case 2:
				displayCategories();// display categories
				PrintUtilities.pause();
				break;
			case 3:
				/*
				 * if there any categories, ask user for category id to update that one
				 */
				if (displayCategories()) {// start if
					System.out.print(" Enter category id to update:");
					cid = scnr.next();
					// update category
					icdi.updateCategory(cid);
				} // end if
				PrintUtilities.pause();
				break;
			case 4:
				/*
				 * if there any categories, ask user for category id to delete that one
				 */
				if (displayCategories()) {
					System.out.print(" Enter category id to delete:");
					cid = scnr.next();
					// delete category
					icdi.deleteCategory(cid);
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
		} // end of while
	}

	/**
	 * displays all the categories, returns true if found else false
	 * 
	 * @return true if found, else false
	 */
	public static boolean displayCategories() {
		// list of insurance category
		List<InsuranceCategory> icategories = icdi.viewCategory();

		// if the categories list is not empty; show categories; if not show message
		if (icategories.size() != 0) {// start if
			PrintUtilities.displayListTitle("ALL CATEGORIES");
			for (InsuranceCategory ic1 : icategories) {
				PrintUtilities.displayListItem("C_ID: " + ic1.getCid());
				PrintUtilities
						.displayListItem("Category Name: " + ic1.getCname());
				PrintUtilities.displayDividerLine();
			}
			return true;
		} else {
			System.out.println(
					" No insurance categories registered in the system.");
			return false;
		} // end if
	}
}
