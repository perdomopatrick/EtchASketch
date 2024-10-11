package ui.menu;

import model.Canvas;

// Draw Menu
public class DrawMenu extends Menu {

    // EFFECTS: sets quit flag to false
    public DrawMenu() {
        quit = false;
    }

    // EFFECTS: Draw menu, gets the user's valid input then sends input to be
    // evaluated, loops until quit is true (option: String int, shake, quit)
    // quit flag to false
    @Override
    public void menu() {
        String command;
        quit = false;
        do {
            viewCanvas();
            prompt();
            command = scanner.nextLine().trim();
            evaluateStrInput(command);
        } while (!quit);
    }

    // EFFECTS: displays the draw menu options to the user
    @Override
    public void prompt() {
        System.out.println("\n=== Draw Menu ===");
        System.out.println("- Direction and Length");
        System.out.println("- Shake (shake, s)");
        System.out.println("- Quit (quit, q)");
        System.out.print("Choose an option: ");
    }

    /*
     * MODIFIES: this
     * EFFECTS: evaluates the user's input command and performs the corresponding
     * action
     * If the command is a String and Int, sends move to play method
     * If the command is "shake" or "s", it go to shake method
     * If the command is "quit" or "q", it exits the view menu and sets the quit
     * flag to true
     * ignores case sensitivity for the command
     */
    @Override
    protected void evaluateStrInput(String command) {
        int inputInt;
        String inputStr;
        if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("q")) {
            System.out.println("Exiting the Draw Menu");
            quit = true;
        } else if (command.equalsIgnoreCase("shake") || command.equalsIgnoreCase("s")) {
            shake();
        } else {
            try {
                String[] parts = command.split(" ");
                inputStr = parts[0];
                inputInt = Integer.parseInt(parts[1]);
                play(inputStr, inputInt);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! The first input must be a String");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid input! Input must be an String and integer separated by a space");
            }
        }
    }

    // EFFECTS: trys to draw on the canvas for given move
    public void play(String direction, int length) {
        Canvas canvas = gallery.getCurrCanvas();
        boolean successfulMove = false;
        if (length < 0) {
            System.out.println("Invalid length! Input length must be non-zero");
        } else if (direction.equalsIgnoreCase("up") || direction.equalsIgnoreCase("down")
                || direction.equalsIgnoreCase("left") || direction.equalsIgnoreCase("right")) {
            successfulMove = canvas.draw(direction.toLowerCase(), length);
            if (!successfulMove) {
                System.out.println("Stlyus hit the border!");
            }
        } else {
            System.out.println("Invalid input! Input direction must be (up, down, left, or right)");
        }
    }

    // EFFECTS: shakes current canvas
    private void shake() {
        System.out.println("Shaking...");
        Canvas canvas = gallery.getCurrCanvas();
        canvas.shake();
    }

    // EFFECTS: displays the current canvas with stylus coordinates
    private void viewCanvas() {
        Canvas canvas = gallery.getCurrCanvas();
        displayCanvasWithStylus(canvas.getBoard(), canvas.getStylusXCoord(), canvas.getStylusYCoord());
        System.out.println("\nStylus Coords: (" + canvas.getStylusXCoord() + "," + canvas.getStylusYCoord() + ")");
    }
}
