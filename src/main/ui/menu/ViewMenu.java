package ui.menu;

public class ViewMenu extends Menu {
    private final DrawMenu drawMenu;

    // EFFECTS: creates instance with a new DrawMenu
    // and sets quit to false
    public ViewMenu() {
        drawMenu = new DrawMenu();
        quit = false;
    }

    // EFFECTS: View menu, gets the user's valid input then sends input to be
    // evaluated, loops until quit is true (option: next, prev, draw, quit)
    // The current canvas is reset at the start
    public void menu() {
        String choice;
        gallery.resetCurrCanvas();
        do {
            viewCanvas();
            choice = getInputStr("next", "n", "prev", "p", "draw", "d", "quit", "q");
            evaluateStrInput(choice);
        } while (!quit);
    }

    // EFFECTS: Displays the view menu options to the user
    public void prompt() {
        System.out.println("\n=== View Menu ===");
        System.out.println("Next cavnas (next, n)");
        System.out.println("Prev cavnas (prev, v)");
        System.out.println("Draw (draw, d)");
        System.out.println("Quit (quit, q)");
        System.out.print("Choose an option: ");
    }

    /*
     * MODIFIES: this
     * EFFECTS: Evaluates the user's input command and performs the corresponding
     * action
     * If the command is "next" or "n", it navigates to the next canvas
     * If the command is "prev" or "p", it navigates to the previous canvas
     * If the command is "draw" or "d", it opens the draw menu and attempts to draw
     * on the current canvas
     * If the command is "quit" or "q", it exits the view menu and sets the quit
     * flag to true
     * Ignores case sensitivity for the command
     */
    protected void evaluateStrInput(String command) {
        if (command.equalsIgnoreCase("next") || command.equalsIgnoreCase("n")) {
            next();
        } else if (command.equalsIgnoreCase("prev") || command.equalsIgnoreCase("p")) {
            prev();
        } else if (command.equalsIgnoreCase("draw") || command.equalsIgnoreCase("d")) {
            try {
                drawMenu.menu();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("No canvas to draw on");
            }
        } else if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("q")) {
            System.out.println("Exiting...");
            quit = true;
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    // EFFECTS: Displays the current canvas's board, if it exists
    private void viewCanvas() {
        try {
            displayCanvas(gallery.getCurrCanvas().getBoard());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid input! No canvas");
        }
    }

    // EFFECTS: Advances to the next canvas in the gallery
    private void next() {
        gallery.nextCanvas();
    }

    // EFFECTS: Advances to the previous canvas in the gallery
    private void prev() {
        gallery.prevCanvas();
    }
}
