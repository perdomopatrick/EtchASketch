package ui.cli.menu;

import java.util.Scanner;

import model.Gallery;

// Menu abstract
public abstract class Menu {
    protected final Scanner scanner;
    protected Boolean quit;
    protected Gallery gallery;

    // EFFECTS: creates a Scanner for input and
    // obtains the singleton instance of Gallery
    public Menu() {
        scanner = new Scanner(System.in);
        this.gallery = Gallery.getInstance();
    }

    // EFFECTS: abstract method, calls prompt to be displayed and
    // gets the user's valid input then sends input to be evaluated
    protected abstract void menu();

    // EFFECTS: abstract method, displays user's options 
    protected abstract void prompt();

    // EFFECTS: abstract method, evaluates user's input command and performs the
    // corresponding action
    protected abstract void evaluateStrInput(String command);

    // EFFECTS: prompts the user, validates it against allowed values,
    // and returns the valid input string, and loop until valid input
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

    // EFFECTS: displays the canvas with the stylus coordinates in red
    // true is represented as 1 and false as 0
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

    // EFFECTS: displays the canvas; true is represented as 1 and false as 0
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