package com.ims.client;

import java.util.List;
import java.util.Scanner;

import com.ims.dao.impl.InsuranceSubCategoryDAOImpl;
import com.ims.pojo.InsuranceSubCategory;
import com.ims.utility.PrintUtilities;
import com.ims.utility.ValidatorUtilities;

public class SubCategoryMenu {
	// static variables of the class
	static Scanner scnr = new Scanner(System.in);
	static InsuranceSubCategoryDAOImpl iscdi = new InsuranceSubCategoryDAOImpl();

	/**
	 * displays menu options using helper methods in Utilities class
	 */
	public static void displayMenu() {
		PrintUtilities.displayTitle("SUB-CATEGORY MENU");
		PrintUtilities.printBlankLines(3);
		PrintUtilities.displayChoice("1) ADD SUB-CATEGORY");
		PrintUtilities.displayChoice("2) VIEW ALL SUB-CATEGORY");
		PrintUtilities.displayChoice("3) UPDATE SUB-CATEGORY");
		PrintUtilities.displayChoice("4) DELETE SUB-CATEGORY");
		PrintUtilities.displayChoice("5) Back");
		PrintUtilities.displayEndLine();
		PrintUtilities.displayChoicePrompt();
	}

	/**
	 * display the subcategory dashboard if the menu is selected
	 */
	static void displaySubCategoryDashboard() {
		// local variables
		String scid;
		String cid;

		while (true) {// start while
			displayMenu();// show menu
			switch (ValidatorUtilities.getInteger("choice")) {
			case 1:
				// if there are any categories, ask user to select a category to add
				// sub-categories into
				if (CategoryMenu.displayCategories()) {// start if
					System.out.print(
							" Select a category id to add sub-category into:");
					cid = scnr.next();

					// add sub-category
					iscdi.addSubCategory(cid);
				} // end if
				PrintUtilities.pause();
				break;
			case 2:
				// if there are any categories, ask user to select a category to view
				// sub-categories
				if (CategoryMenu.displayCategories()) {// start if
					System.out.print(
							" Select a category id to view sub-categories:");
					cid = scnr.next();

					// display all sub-categories in selected category
					displayAllSubCategories(cid);
				} // end if
				PrintUtilities.pause();
				break;
			case 3:
				// if there are any categories, ask user to select a category to update
				// sub-categories
				if (CategoryMenu.displayCategories()) {// start outer if
					System.out.print(
							" Select a category id to update sub-categories:");
					cid = scnr.next();
					// if there are any sub-categories, ask user to select a sub-categories
					// to update
					if (displayAllSubCategories(cid)) {// start inner if
						System.out.print(" Enter Sub-category id to update:");
						scid = scnr.next();

						// update the selected sub-category
						iscdi.updateSubCategory(cid, scid);
					} // end inner if
				} // end outer if
				PrintUtilities.pause();
				break;
			case 4:
				// if there are any categories, ask user to select a category to delete
				// sub-categories
				if (CategoryMenu.displayCategories()) {// start outer if
					System.out.print(
							" Select a category id to delete sub-categories:");
					cid = scnr.next();
					// if there are any sub-categories, ask user to select a sub-categories
					// to delete
					if (displayAllSubCategories(cid)) {// start inner if
						System.out.print(" Enter Sub-category id to delete:");
						scid = scnr.next();

						// delete the selected sub-category
						iscdi.deleteSubCategory(cid, scid);
					} // end inner if
				} // end outer if
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
	 * displays all the subcategories in the selected category
	 * 
	 * @param cid, category id
	 * @return true if sub-categories found, else false
	 */
	public static boolean displayAllSubCategories(String cid) {
		// list of all sub-categories of the selected category
		List<InsuranceSubCategory> allSubCategories = iscdi
				.viewSubCategory(cid);
		// if it is not null, display all of them; else show message that none found
		if (allSubCategories != null && allSubCategories.size() != 0) {// start if
			PrintUtilities
					.displayListTitle("SUB-CATEGORIES UNDER SELECTED POLICY");
			// loop though all categories and display each
			for (InsuranceSubCategory isc1 : allSubCategories) {
				PrintUtilities
						.displayListItem("SUB_C_ID: " + isc1.getSubcid());
				PrintUtilities.displayListItem(
						"Sub-category Name: " + isc1.getSubcname());
				PrintUtilities.displayDividerLine();
			}
			return true;
		} else {
			System.out.println(
					" No insurance sub-categories found in the category with C_ID: "
							+ cid);
			return false;
		} // end if
	}
}
