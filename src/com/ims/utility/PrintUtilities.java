package com.ims.utility;

import java.util.Scanner;

/**
 * Class containing all the helper methods to print the screen, pause
 */
public class PrintUtilities {
	final static int LINE_LEN = 80;

	public static void displayTitle(String title) {
		int spacing = LINE_LEN - title.length();
		int firstSpace = spacing / 2;
		int lastSpace = spacing / 2;
		if (spacing % 2 != 0) {
			firstSpace = spacing / 2;
			lastSpace = (spacing / 2) + 1;
		}
		System.out.println("_" + printSingleLine() + "_");
		justifyMiddle(firstSpace, title, lastSpace);
		displayEndLine();
	}

	public static void displayListTitle(String title) {
		int spacing = LINE_LEN - title.length();
		int firstSpace = spacing / 2;
		int lastSpace = spacing / 2;
		if (spacing % 2 != 0) {
			firstSpace = spacing / 2;
			lastSpace = (spacing / 2) + 1;
		}
		displayDividerLine();
		justifyMiddle(firstSpace, title, lastSpace);
		displayDividerLine();
	}

	public static void displayListItem(String item) {
		int spacing = LINE_LEN - item.length();
		System.out.print("|");
		System.out.print(item);
		for (int i = 0; i < spacing; i++) {
			System.out.print(" ");
		}
		System.out.println("|");
	}

	public static void displayChoice(String choice) {
		int spacing = LINE_LEN;
		int firstSpace = spacing / 3;
		int lastSpace = LINE_LEN - choice.length() - firstSpace;

		justifyMiddle(firstSpace, choice.toUpperCase(), lastSpace);
		printBlankLines(2);
	}

	private static void justifyMiddle(int firstSpace, String value,
			int lastSpace) {
		System.out.print("|");
		for (int i = 0; i < firstSpace; i++) {
			System.out.print(" ");
		}
		System.out.print(value);
		for (int i = 0; i < lastSpace; i++) {
			System.out.print(" ");
		}
		System.out.println("|");
	}

	public static void displayDividerLine() {
		System.out.print("|");
		for (int i = 0; i < LINE_LEN; i++) {
			System.out.print("-");
		}
		System.out.println("|");
	}

	public static void displayEndLine() {
		System.out.println("|" + printSingleLine() + "|");
	}

	public static void displayChoicePrompt() {
		System.out.print(" Please enter your choice:");
	}

	public static void printBlankLines(int spacing) {
		while (spacing > 0) {
			System.out.print("|");
			for (int i = 0; i < LINE_LEN; i++) {
				System.out.print(" ");
			}
			System.out.print("|\n");
			spacing--;
		}
	}

	private static String printSingleLine() {
		String result = "";
		for (int i = 0; i < LINE_LEN; i++) {
			result += ("_");
		}
		return result;
	}

	public static void pause() {
		System.out.print("\n Press \"ENTER\" to continue...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
	}
}
