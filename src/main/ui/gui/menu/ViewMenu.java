package ui.gui.menu;

import model.Canvas;

import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewMenu extends Menu {

    private JPanel canvasPanel;
    private Canvas canvas;

    // EFFECTS: Initializes ViewMenu with the given mainMenu
    public ViewMenu(MainMenu mainMenu) {
        super(mainMenu);
    }

    // EFFECTS: Constructs and returns a JPanel representing the ViewMenu,
    // sets up the button panels and canvas panel
    @Override
    public JPanel menu() {
        JPanel panel = new JPanel(new BorderLayout());

        createCanvasPanel();
        JPanel navigationButtons = createNavigationButtons();
        JPanel quitAndSavePanel = createQuitAndExportButtons();
        JScrollPane scrollPane = new JScrollPane(canvasPanel);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(navigationButtons, BorderLayout.SOUTH);
        panel.add(quitAndSavePanel, BorderLayout.NORTH);

        return panel;
    }

    // EFFECTS: Creates and returns a panel containing quit and export buttons
    // quit button returns to the main menu, export button sends to helper to export the canvas to a `.png` file
    private JPanel createQuitAndExportButtons() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> mainMenu.showCard("MainMenu"));

        JButton exportButton = new JButton("Export to .png");

        exportButton.addActionListener(e -> saveCanvasToFile());

        buttonPanel.add(quitButton);
        buttonPanel.add(exportButton);

        return buttonPanel;
    }

    // MODIFIES: this
    // EFFECTS: Saves the current canvas as a `.png` image file with timestamped filename
    // If the canvas is not available, displays an error message
    private void saveCanvasToFile() {
        try {
            canvas = gallery.getCurrCanvas();
            int width = canvas.getBoard()[0].length;
            int height = canvas.getBoard().length;
            int cellSize = 20;

            BufferedImage image = new BufferedImage(width * cellSize, height * cellSize,
                    BufferedImage.TYPE_BYTE_BINARY);

            displayCanvas(canvas.getBoard(), image.createGraphics(), cellSize, cellSize);

            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String filePath = "./data/savedCanvas_" + timestamp + ".png";
            try {
                ImageIO.write(image, "png", new File(filePath));
                JOptionPane.showMessageDialog(canvasPanel, "Canvas saved as: " + filePath);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(canvasPanel, "Unable to save to " + filePath);
            }
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(canvasPanel, "No canvas to save");
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates a canvas panel that displays the current canvas, 
    // paints the canvas on the panel. If no canvas is available, an error message is shown
    private void createCanvasPanel() {
        JLabel errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);

        canvasPanel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    canvas = gallery.getCurrCanvas();

                    int cellWidth = Math.max(mainMenu.getWidth() / canvas.getBoard()[0].length, 1);
                    int cellHeight = Math.max(mainMenu.getHeight() / canvas.getBoard().length, 1);

                    canvasPanel.setPreferredSize(new Dimension(canvas.getBoard()[0].length * cellWidth,
                            canvas.getBoard().length * cellHeight));

                    displayCanvas(canvas.getBoard(), g, cellWidth, cellHeight);
                    canvasPanel.revalidate();
                    errorLabel.setText("");
                } catch (IndexOutOfBoundsException e) {
                    errorLabel.setText("No canvas available");
                    errorLabel.setVisible(true);
                }
            }
        };
        canvasPanel.add(errorLabel, BorderLayout.SOUTH);
    }

    // EFFECTS: Creates and returns a panel containing three navigation buttons,
    // "Next Canvas" navigates to the next canvas, "Previous Canvas" navigates to the previous canvas, and 
    // "Draw" refreshing the draw menu (which switch the mainpanel to the draw panel)
    private JPanel createNavigationButtons() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));

        JButton nextButton = new JButton("Next Canvas");
        nextButton.addActionListener(e -> next());

        JButton prevButton = new JButton("Previous Canvas");
        prevButton.addActionListener(e -> prev());

        JButton drawButton = new JButton("Draw");
        drawButton.addActionListener(e -> mainMenu.refreshDrawMenu());

        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(drawButton);

        return buttonPanel;
    }

    // EFFECTS: Advances to the next canvas in the gallery and repaints
    private void next() {
        gallery.nextCanvas();
        canvasPanel.repaint();
    }

    // EFFECTS: Advances to the previous canvas in the gallery and repaints
    private void prev() {
        gallery.prevCanvas();
        canvasPanel.repaint();
    }
}
