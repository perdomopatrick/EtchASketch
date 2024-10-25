package ui.menu;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONException;

import model.Gallery;
import persistence.JsonReader;
import persistence.JsonWriter;

// Main Menu
public class MainMenu extends Menu {

    private final ViewMenu viewMenu;
    private final NewCanvasMenu newCanvasMenu;

    private static final String JSON_STORE = "./data/gallery.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: creates instances of ViewMenu and NewCanvasMenu,
    // and sets quit to false
    public MainMenu() {
        viewMenu = new ViewMenu();
        newCanvasMenu = new NewCanvasMenu();
        quit = false;

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: Main menu, gets the user's valid input then sends input to be
    // evaluated
    // loops until quit is true (option: new, view, quit)
    @Override
    public void menu() {
        String choice;
        do {
            choice = getInputStr("new", "n", "view", "v", "quit", "q", "load", "l", "save", "s");
            evaluateStrInput(choice);
        } while (!quit);
    }

    // EFFECTS: displays the main menu options to the user
    @Override
    public void prompt() {
        System.out.println("=== Main Menu ===");
        System.out.println("- New canvas (new, n)");
        System.out.println("- View gallery (view, v)");
        System.out.println("- Load saved gallery  (load, l)");
        System.out.println("- Save gallery (save, s)");
        System.out.println("- Quit (quit, q)");
        System.out.print("Choose an option: ");
    }

    // MODIFIES: this
    // EFFECTS: evaluates the user's input command and performs the corresponding
    // action
    // If the command is "new" or "n", it opens the new canvas menu
    // If the command is "view" or "v", it opens the view canvas menu
    // If the command is "load" or "l", it goes to load method
    // If the command is "save" or "s", it goes to save method
    // If the command is "quit" or "q", it exits the game and sets the quit flag to
    // true
    // ignores case sensitivity for the command
    @Override
    protected void evaluateStrInput(String command) {
        if (command.equalsIgnoreCase("new") || command.equalsIgnoreCase("n")) {
            newCanvasMenu.menu();
        } else if (command.equalsIgnoreCase("view") || command.equalsIgnoreCase("v")) {
            viewMenu.menu();
        } else if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("q")) {
            System.out.println("Exiting the game...");
            quit = true;
        } else if (command.equalsIgnoreCase("load") || command.equalsIgnoreCase("l")) {
            load();
        } else {
            save();
        }
    }

    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(gallery);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void load() {
        try {
            Gallery deserializedGallery = jsonReader.read();
            Gallery.getInstance().updateFrom(deserializedGallery);
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        } catch (JSONException e) {
            System.out.println("Unable to prase data from found file: " + JSON_STORE);
        }
    }
}
