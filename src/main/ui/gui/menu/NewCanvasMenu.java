package ui.gui.menu;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// New Canvas Menu
public class NewCanvasMenu extends Menu {

    // EFFECTS: Initializes NewCanvasMenu with the given mainMenu
    public NewCanvasMenu(MainMenu mainMenu) {
        super(mainMenu);
    }

    // EFFECTS: Constructs and returns a JPanel representing the NewCanvasMenu,
    // sets up the text fields and create Canvas button
    @Override
    public JPanel menu() {
        JPanel panel = new JPanel(new FlowLayout());

        JTextField widthField = new JTextField(5);
        JTextField heightField = new JTextField(5);
        JLabel errorLabel = createErrorLabel();

        JButton createButton = createCanvasButton(widthField, heightField, errorLabel);

        panel.add(new JLabel("Width:"));
        panel.add(widthField);
        panel.add(new JLabel("Height:"));
        panel.add(heightField);
        panel.add(createButton);
        panel.add(errorLabel);

        return panel;
    }

    // EFFECTS: Creates and returns a label used for displaying error messages 
    private JLabel createErrorLabel() {
        JLabel errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        return errorLabel;
    }

    // EFFECTS: Creates and returns a button. When clicked, it attempts to parse the width and height values 
    // from the text fields, if the values are invalid, it updates the error label with a message
    private JButton createCanvasButton(JTextField widthField, JTextField heightField, JLabel errorLabel) {
        JButton createButton = new JButton("Create Canvas");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int height = Integer.parseInt(heightField.getText().trim());
                    int width = Integer.parseInt(widthField.getText().trim());

                    if (height > 0 && width > 0) {
                        gallery.newCanvas(width, height);
                        mainMenu.refreshDrawMenu();
                    } else {
                        errorLabel.setText("Please enter positive values for both width and height.");
                    }
                } catch (NumberFormatException ex) {
                    errorLabel.setText("Invalid input. Please enter valid integers.");
                }
            }
        });
        return createButton;
    }
}
