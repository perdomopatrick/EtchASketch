package ui.gui.menu;

import java.awt.FlowLayout;
import java.io.File;
import java.util.Random;

import javax.swing.*;

// VisualComponent menu; guess the country game based off an image of its outline 
public class VisualComponent extends Menu {

    private String countryName;
    private JPanel panel;
    private static final String COUNTRY_PATH = "./data/countriesImages";

    // EFFECTS: Initializes VisualComponent a with the given mainMenu
    public VisualComponent(MainMenu mainMenu) {
        super(mainMenu);
    }

    // MODIFIES: this
    // EFFECTS: Constructs and returns a JPanel representing the VisualComponent menu,
    // sets up the image, text field and buttons, 
    @Override
    public JPanel menu() {
        panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel();
        panel.add(label);

        JTextField textField = new JTextField(20);
        JButton enterButton = new JButton("Enter");
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> mainMenu.showCard("MainMenu"));
        getRandomCountryName(label);

        enterButton.addActionListener(e -> {
            String userInput = textField.getText().toLowerCase().replaceAll("\\s+", "");

            if (userInput.equalsIgnoreCase(countryName)) {
                JOptionPane.showMessageDialog(panel, "Correct!");
                textField.setText("");
                getRandomCountryName(label);
            } else {
                JOptionPane.showMessageDialog(panel, "Incorrect! Try again!");
            }
        });
        panel.add(textField);
        panel.add(enterButton);
        panel.add(quitButton);
        return panel;
    }

    // REQUIRES: label != null
    // MODIFIES: this
    // EFFECTS: Sets the countryName to a random country image file from the COUNTRY_PATH directory and 
    // updates the provided gievm label with the selected country image
    private void getRandomCountryName(JLabel label) {
        File folder = new File(COUNTRY_PATH);
        File[] files = folder.listFiles();
        Random random = new Random();
        try {
            File randomFile = files[random.nextInt(files.length)];
            countryName = randomFile.getName().replaceAll("\\.png$", "");

            ImageIcon image = new ImageIcon(COUNTRY_PATH + "/" + randomFile.getName());
            label.setIcon(image);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(panel, "Path " + COUNTRY_PATH + " doesn't exist");

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(panel, "No files! in " + COUNTRY_PATH);
        }

    }

}
