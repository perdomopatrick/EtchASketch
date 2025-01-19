package ui.gui.menu;

import model.Canvas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

// Draw Menu
public class DrawMenu extends Menu {
   
    private JPanel panel;
    private JPanel drawingPanel;
    private JPanel controlPanel;
    
    private int keyUp;
    private int keyDown;
    private int keyLeft;
    private int keyRight;

    private JMenuItem upControls;
    private JMenuItem downControls;
    private JMenuItem leftControls;
    private JMenuItem rightControls;

    private JMenuBar menuBar;
    private JMenu settingsMenu;

    // EFFECTS: Initializes DrawMenu with the given mainMenu and sets up key bindings
    public DrawMenu(MainMenu mainMenu) {
        super(mainMenu);

        keyUp = KeyEvent.VK_UP;
        keyDown = KeyEvent.VK_DOWN;
        keyLeft = KeyEvent.VK_LEFT;
        keyRight = KeyEvent.VK_RIGHT;
    }

    // MODIFIES: this
    // EFFECTS: Constructs and returns a JPanel representing the DrawMenu,
    // sets up the drawing panel, control panel, and button panel
    @Override
    public JPanel menu() {
        panel = new JPanel(new BorderLayout());
        try {
            gallery.getCurrCanvas();
        } catch (IndexOutOfBoundsException e) {
            panel.setLayout(new FlowLayout());
            panel.add(new JLabel("No canvas created yet"));
            panel.add(createQuitButton());
            return panel;
        }

        createDrawingPanel();
        createControlPanel();
        JPanel buttonPanel = createButtons();

        JScrollPane scrollPane = new JScrollPane(drawingPanel);

        removeAutoScroll(scrollPane);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        remapMenuBar();

        SwingUtilities.invokeLater(() -> drawingPanel.requestFocusInWindow());
        return panel;
    }

    // EFFECTS: removes auto scroll from the given scroll pane
    private void removeAutoScroll(JScrollPane scrollPane) {
        InputMap inputMap = scrollPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke("UP"), "none");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "none");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "none");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "none");
    }

    // MODIFIES: this, mainMenu
    // EFFECTS: Creates and sets a new menu bar for the frame,
    // The menu allows the user to remap the keys
    private void remapMenuBar() {
        menuBar = new JMenuBar();
        settingsMenu = new JMenu("Remap Controls");
        settingsMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        upControls = new JMenuItem("Remap Up: " + KeyEvent.getKeyText(keyUp));
        downControls = new JMenuItem("Remap Down: " + KeyEvent.getKeyText(keyDown));
        leftControls = new JMenuItem("Remap Left: " + KeyEvent.getKeyText(keyLeft));
        rightControls = new JMenuItem("Remap Right: " + KeyEvent.getKeyText(keyRight));

        upControls.addActionListener(e -> remapKey("Up"));
        downControls.addActionListener(e -> remapKey("Down"));
        leftControls.addActionListener(e -> remapKey("Left"));
        rightControls.addActionListener(e -> remapKey("Right"));

        settingsMenu.add(upControls);
        settingsMenu.add(downControls);
        settingsMenu.add(leftControls);
        settingsMenu.add(rightControls);

        menuBar.add(settingsMenu);
        mainMenu.setJMenuBar(menuBar);
    }

    // MODIFIES: this
    // EFFECTS: Removes the ermap menu from the menu bar
    private void removeMenuBar() {
        if (menuBar != null) {
            menuBar.remove(settingsMenu);
        }
    }

    // EFFECTS: Creates and returns a panel containing quit and shake buttons,
    // Shake button triggers the canvas to shake and repaints the drawing panel
    private JPanel createButtons() {
        JButton quitButton = createQuitButton();
        JButton shakeButton = new JButton("Shake");
        shakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gallery.getCurrCanvas().shake();
                drawingPanel.repaint();
                SwingUtilities.invokeLater(() -> drawingPanel.requestFocusInWindow());
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        buttonPanel.add(quitButton);
        buttonPanel.add(shakeButton);

        return buttonPanel;
    }

    // MODIFIES: this
    // EFFECTS: Creates and adds control panel with the current key controls to the main panel
    private void createControlPanel() {
        if (controlPanel != null) {
            panel.remove(controlPanel);
        }
        controlPanel = new JPanel(new GridLayout(1, 4));
        controlPanel.add(new JLabel("Left (" + KeyEvent.getKeyText(keyLeft) + ")", JLabel.CENTER));
        controlPanel.add(new JLabel("Right (" + KeyEvent.getKeyText(keyRight) + ")", JLabel.CENTER));
        controlPanel.add(new JLabel("Up (" + KeyEvent.getKeyText(keyUp) + ")", JLabel.CENTER));
        controlPanel.add(new JLabel("Down (" + KeyEvent.getKeyText(keyDown) + ")", JLabel.CENTER));
        panel.add(controlPanel, BorderLayout.SOUTH);
        panel.revalidate();
        panel.repaint();
    }

    // EFFECTS: Creates and returns a the quit button,
    // when clicked, it removes the menu bar and goes to the mainmenu
    private JButton createQuitButton() {
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> {
            removeMenuBar();
            mainMenu.showCard("MainMenu");
        });
        return quitButton;
    }

    // MODIFIES: this
    // EFFECTS:  Initializes the drawingPanel and the panel is updated on each repaint
    // to display the current canvas and the stylus position, makes helper set up key listener
    private void createDrawingPanel() {
        drawingPanel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Canvas canvas = gallery.getCurrCanvas();

                int cellWidth = Math.max(mainMenu.getWidth() / canvas.getBoard()[0].length, 1);
                int cellHeight = Math.max(mainMenu.getHeight() / canvas.getBoard().length, 1);

                drawingPanel.setPreferredSize(new Dimension(canvas.getBoard()[0].length * cellWidth,
                        canvas.getBoard().length * cellHeight));

                displayCanvasWithStylus(canvas.getBoard(), g, canvas.getStylusXCoord(),
                        canvas.getStylusYCoord(), cellWidth, cellHeight);
            }
        };
        drawingPanel.setFocusable(true);
        drawingPanelKeyListener();
    }

    // MODIFIES: this
    // EFFECTS: Adds a key listener to the drawingPanel,
    // updates the canvas and repaints drawing panel when a key is pressed, 
    // calling the draw method with the corresponding direction
    private void drawingPanelKeyListener() {
        drawingPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                Canvas canvas = gallery.getCurrCanvas();

                if (keyCode == keyRight) {
                    canvas.draw("right", 1);
                } else if (keyCode == keyLeft) {
                    canvas.draw("left", 1);
                } else if (keyCode == keyUp) {
                    canvas.draw("up", 1);
                } else if (keyCode == keyDown) {
                    canvas.draw("down", 1);
                }
                drawingPanel.repaint();
                createControlPanel();
            }
        });
    }

    // REQUIRES: action equals one of "Up", "Down", "Left", "Right"
    // EFFECTS: Opens a dialog where the user can press any key to remap controls
    // Has helper for key listening 
    private void remapKey(String action) {
        JDialog dialog = new JDialog((JFrame) null, "Press Any Key to Remap: " + action, true);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(drawingPanel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("Press any key to remap the action", SwingConstants.CENTER);
        dialog.add(label, BorderLayout.CENTER);

        dialog.setFocusable(true);
        dialog.requestFocusInWindow();

        dialogKeyListener(action, dialog);

        dialog.setVisible(true);

        createControlPanel();
    }

    // MODIFIES: this
    // REQUIRES: dialog != null && action equals one of "Up", "Down", "Left", "Right"
    // EFFECTS: The dialog listens for key presses and updates the corresponding control key
    private void dialogKeyListener(String action, JDialog dialog) {
        dialog.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (action.equals("Up")) {
                    keyUp = keyCode;
                    upControls.setText("Remap Up: " + KeyEvent.getKeyText(keyUp));
                } else if (action.equals("Down")) {
                    keyDown = keyCode;
                    downControls.setText("Remap Down: " + KeyEvent.getKeyText(keyDown));
                } else if (action.equals("Left")) {
                    keyLeft = keyCode;
                    leftControls.setText("Remap Left: " + KeyEvent.getKeyText(keyLeft));
                } else if (action.equals("Right")) {
                    keyRight = keyCode;
                    rightControls.setText("Remap Right: " + KeyEvent.getKeyText(keyRight));
                }

                JOptionPane.showMessageDialog(drawingPanel, action + " remapped to: " + KeyEvent.getKeyText(keyCode));
                dialog.dispose();
            }
        });
    }
}
