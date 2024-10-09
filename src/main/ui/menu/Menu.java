package ui.menu;

import java.util.Scanner;

import model.Gallery;

public abstract class Menu {
	protected final Scanner scanner;
	protected Boolean quit;
	protected Gallery gallery;

	public Menu() {
		scanner = new Scanner(System.in);
		this.gallery = Gallery.getInstance();
	}
	
	protected abstract void menu();
	protected abstract void prompt();
	protected abstract void evaluateStrInput(String command);

	protected String getInputStr(String... allowedStr) {
		String userInput = "";
		boolean validInput = false;
		do {
			try {
				prompt();
				userInput = scanner.nextLine();
				boolean isAllowed = false;
				for (String str : allowedStr) {
					if (userInput.equals(str)) {
						isAllowed = true;
						break;
					}
				}
				if (!isAllowed) {
					throw new IllegalArgumentException("Input not allowed."); 
				}
				validInput = true; 
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		} while (!validInput);
		return userInput;
	}

	protected void displayCanvasWithStylus(boolean[][] b, int stylusXCoord, int stylusYCoord) {
		System.out.print("\n   "); 
		for (int i = 0; i < b[0].length; i++) {
			System.out.printf("%3d", i); 
		}
		for (int i = 0; i < b.length; i++) {
			System.out.printf("%s%3d", "\n", i);
			for (int j = 0; j < b[i].length; j++) {
				if (j == stylusXCoord && i == stylusYCoord) {
					System.out.printf("%s%3d%s", "\u001B[31m", (b[i][j] ? 1 : 0), "\u001B[0m");
				} else {
					System.out.printf("%3d", (b[i][j] ? 1 : 0));
				}
			}
		}
	}

	protected void displayCanvas(boolean[][] b) {
		System.out.print("\n   "); 
		for (int i = 0; i < b[0].length; i++) {
			System.out.printf("%3d", i); 
		}
		for (int i = 0; i < b.length; i++) {
			System.out.printf("%s%3d", "\n", i);
			for (int j = 0; j < b[i].length; j++) {
				System.out.printf("%3d", (b[i][j] ? 1 : 0));
			}
		}
	}
}