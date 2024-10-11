package ui.menu;

// New Canvas Menu
public class NewCanvasMenu extends Menu {

    private final DrawMenu drawMenu;
    private int height;
    private int width;

    // EFFECTS: creates instance with a new DrawMenu
    // and sets quit to false and dimensions to 0
    public NewCanvasMenu() {
        drawMenu = new DrawMenu();
        quit = false;
        height = 0;
        width = 0;
    }

    // EFFECTS: New Canvas Menu, gets the user's valid input then
    // creates canvas with given height and width and sends user to Draw menu
    @Override
    public void menu() {
        String command;
        do {
            prompt();
            command = scanner.nextLine().trim();
            evaluateStrInput(command);
        } while (!quit);

        System.out.println("Canvas created with Height: " + height + " and Width: " + width);
        gallery.newCanvas(width, height);
        drawMenu.menu();
    }

    // EFFECTS: displays the new canvas menu options to the user
    @Override
    public void prompt() {
        System.out.println("\n=== New Canvas ===");
        System.out.println("Please specify the dimensions of your canvas");
        System.out.print("Enter Height and Width (e.g., '10 20'): ");
    }

    /*
     * MODIFIES: this
     * EFFECTS: evaluates the input command for two positive integers separated by a
     * space
     * If valid, sets the height and width values and marks the input process as
     * complet, quit = true else == false
     */
    @Override
    protected void evaluateStrInput(String command) {
        if (command.matches("[1-9][0-9]* [1-9][0-9]*")) {
            String[] parts = command.split(" ");
            height = Integer.parseInt(parts[0]);
            width = Integer.parseInt(parts[1]);
            quit = true;
        } else {
            System.out.println("Invalid input. Please enter two positive integers separated by a space");
            quit = false;
        }
    }
}
