package ui.gui.menu;

import java.awt.FlowLayout;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


// VisualComponent menu; guess the country game based off an image of its outline 
public class VisualComponent extends Menu {

    private String countryName;
    private JPanel panel;
    private JLabel label;
    private static final String COUNTRY_PATH = "./data/countriesImages";

    // EFFECTS: Initializes VisualComponent a with the given mainMenu
    public VisualComponent(MainMenu mainMenu) {
        super(mainMenu);
        
        panel = new JPanel(new FlowLayout());
        label = new JLabel();
        panel.add(label);
    }

    // MODIFIES: this
    // EFFECTS: Constructs and returns a JPanel representing the VisualComponent menu,
    // sets up the image, text field and buttons, 
    @Override
    public JPanel menu() {
        JTextField textField = new JTextField(20);
        JButton enterButton = new JButton("Enter");
        JButton resetButton = new JButton("New Country");
        JButton quitButton = new JButton("Quit");

        quitButton.addActionListener(e -> mainMenu.showCard("MainMenu"));
        getRandomCountryName(textField);

        enterButtonListener(textField, enterButton);

        resetButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(panel, "Country: " + countryName);
            getRandomCountryName(textField);
        });

        panel.add(textField);
        panel.add(enterButton);
        panel.add(resetButton);
        panel.add(quitButton);
        return panel;
    }

    // EFFECTS: add action listener to give enterButton, when clicked checks 
    // if text in give text field is equal to countryName, then displays dialog based on outcome
    private void enterButtonListener(JTextField textField, JButton enterButton) {
        enterButton.addActionListener(e -> {
            String userInput = textField.getText().toLowerCase().replaceAll("\\s+", "");

            if (userInput.equalsIgnoreCase(countryName)) {
                JOptionPane.showMessageDialog(panel, "Correct!");
                getRandomCountryName(textField);
            } else {
                JOptionPane.showMessageDialog(panel, "Incorrect! Try again!");
            }
        });
    }

    // REQUIRES: label != null
    // MODIFIES: this
    // EFFECTS: Sets the countryName to a random country image file from the COUNTRY_PATH directory and 
    // updates the provided gievm label with the selected country image
    private void getRandomCountryName(JTextField textField) {
        textField.setText("");

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
