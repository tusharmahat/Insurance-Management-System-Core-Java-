package com.ims.utility;

import java.util.Scanner;

/**
 * Class containing methods to validate data-types of user inputs
 */
public class ValidatorUtilities {
	/**
	 * get input from user and checks if the user input is an integer
	 * 
	 * @param value
	 * @return valid user input
	 */
	public static int getInteger(String value) {
		Scanner scanner = new Scanner(System.in);

		while (!scanner.hasNextInt()) {
			System.out.print(" Invalid " + value + ".\n Please enter an integer: ");
			scanner.nextLine();
		}

		int userInput = scanner.nextInt();
		return userInput;
	}

	/**
	 * get input from user and checks if the user input starts with a-z or A-Z
	 * 
	 * @param value
	 * @return valid user input
	 */
	public static String getStringStartingWithAplha(String value) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			String userInput = scanner.next();

			if (userInput.matches("^[a-zA-Z]+.*")) {
				return userInput;
			} else {
				System.out.print(" Invalid " + value + ".\n Please enter a " + value + " starting with alphabet: ");
				scanner.nextLine(); // Consume the rest of the line
			}
		}
	}
}
