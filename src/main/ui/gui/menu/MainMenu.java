package ui.gui.menu;

import model.Gallery;
import persistence.JsonReader;
import persistence.JsonWriter;

import org.json.JSONException;

import javax.swing.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// Main Menu
public class MainMenu extends Menu {

    private final ViewMenu viewMenu;
    private final NewCanvasMenu newCanvasMenu;
    private final DrawMenu drawMenu;
    private final VisualComponent visualComponent;

    private static final String JSON_STORE = "./data/gallery.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JFrame frame;

    // EFFECTS: creates instances of ViewMenu, NewCanvasMenu, VisualComponent, JsonWriter and
    // JsonReader, places all menus in a cardlayout and initializes the frame
    public MainMenu(MainMenu main) {
        super(main);
        viewMenu = new ViewMenu(this);
        newCanvasMenu = new NewCanvasMenu(this);
        drawMenu = new DrawMenu(this);

        visualComponent = new VisualComponent(this);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        mainPanel = new JPanel();
        cardLayout = new CardLayout();

        mainPanel.setLayout(cardLayout);

        mainPanel.add(this.menu(), "MainMenu");
        mainPanel.add(newCanvasMenu.menu(), "NewCanvasMenu");
        mainPanel.add(viewMenu.menu(), "ViewMenu");
        mainPanel.add(visualComponent.menu(), "VisualComponent");
        mainPanel.add(drawMenu.menu(), "DrawMenu");

        frame = new JFrame("Etch A Sketch");
    }

    // MODIFIES: this
    // EFFECTS: Initializes and displays the game window. adds the main panel to the frame,
    // makes the frame visible.
    public void runGame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.setSize(700, 750);
        frame.setVisible(true);
    }

    // EFFECTS: Returns the width of the content pane of the frame, reduced by pixels of padding and borders
    protected int getWidth() {
        return frame.getContentPane().getWidth() - 3;
    }

    // EFFECTS: Returns the height of the content pane of the frame, reduced by pixels of padding, borders, 
    // JMenu, and buttons
    protected int getHeight() {
        return frame.getContentPane().getHeight() - 55 - 5;
    }

    // EFFECTS: Displays the card/panel associated with the given cardName in the cardLayout
    protected void showCard(String cardName) {
        cardLayout.show(mainPanel, cardName);
    }

    // MODIFIES: this
    // EFFECTS: Sets menuBar as the menu bar   
    protected void setJMenuBar(JMenuBar menuBar) {
        frame.setJMenuBar(menuBar);
    }

    // EFFECTS: Refreshes the DrawMenu by removing the current drawing menu panel and
    //          adding a new one, then displaying it.
    protected void refreshDrawMenu() {
        JPanel drawingMenuPanel = drawMenu.menu();

        mainPanel.remove(drawingMenuPanel);
        mainPanel.add(drawingMenuPanel, "DrawMenu");
        showCard("DrawMenu");
    }

    // EFFECTS: creates the main menu with all corresponding components and action listeners
    @Override
    public JPanel menu() {
        JPanel panel = new JPanel(new GridLayout(6, 1));

        JButton newCanvasButton = new JButton("New Canvas");
        newCanvasButton.addActionListener(e -> showCard("NewCanvasMenu"));

        JButton viewCanvasButton = new JButton("View Gallery");
        viewCanvasButton.addActionListener(e -> showCard("ViewMenu"));

        JButton loadButton = new JButton("Load Gallery");
        loadButton.addActionListener(e -> load());

        JButton saveButton = new JButton("Save Gallery");
        saveButton.addActionListener(e -> save());

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));

        JButton visualComponentButton = new JButton("Visual Component");
        visualComponentButton.addActionListener(e -> showCard("VisualComponent"));

        panel.add(newCanvasButton);
        panel.add(viewCanvasButton);
        panel.add(loadButton);
        panel.add(saveButton);
        panel.add(quitButton);
        panel.add(visualComponentButton);

        return panel;
    }

    // EFFECTS: saves the Gallery
    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(Gallery.getInstance());
            jsonWriter.close();
            JOptionPane.showMessageDialog(mainPanel, "Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(mainPanel, "Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: load the Gallery
    private void load() {
        try {
            Gallery deserializedGallery = jsonReader.read();
            Gallery.getInstance().updateFrom(deserializedGallery);
            JOptionPane.showMessageDialog(mainPanel, "Loaded from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mainPanel, "Unable to read from file: " + JSON_STORE);
        } catch (JSONException e) {
            JOptionPane.showMessageDialog(mainPanel, "Unable to parse data from file: " + JSON_STORE);
        }
    }
}