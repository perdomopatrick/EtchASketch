package ui.menu;

// Main Menu
public class MainMenu extends Menu {

    private final ViewMenu viewMenu;
    private final NewCanvasMenu newCanvasMenu;

    // EFFECTS: creates instances of ViewMenu and NewCanvasMenu,
    // and sets quit to false
    public MainMenu() {
        viewMenu = new ViewMenu();
        newCanvasMenu = new NewCanvasMenu();
        quit = false;
    }

    // EFFECTS: Main menu, gets the user's valid input then sends input to be
    // evaluated
    // loops until quit is true (option: new, view, quit)
    public void menu() {
        String choice;
        do {
            choice = getInputStr("new", "n", "view", "v", "quit", "q");
            evaluateStrInput(choice);
        } while (!quit);
    }

    // EFFECTS: displays the main menu options to the user
    public void prompt() {
        System.out.println("=== Main Menu ===");
        System.out.println("- New cavnas (new, n)");
        System.out.println("- View cavnas (view, v)");
        System.out.println("- Quit (quit, q)");
        System.out.print("Choose an option: ");
    }

    // MODIFIES: this
    // EFFECTS: evaluates the user's input command and performs the corresponding
    // action
    // If the command is "new" or "n", it opens the new canvas menu
    // If the command is "view" or "v", it opens the view canvas menu
    // If the command is "quit" or "q", it exits the game and sets the quit flag to
    // true
    // ignores case sensitivity for the command
    protected void evaluateStrInput(String command) {
        if (command.equalsIgnoreCase("new") || command.equalsIgnoreCase("n")) {
            newCanvasMenu.menu();
        } else if (command.equalsIgnoreCase("view") || command.equalsIgnoreCase("v")) {
            viewMenu.menu();
        } else if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("q")) {
            System.out.println("Exiting...");
            quit = true;
        }
    }
}
