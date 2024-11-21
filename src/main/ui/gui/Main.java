package ui.gui;

import javax.swing.SwingUtilities;
import ui.gui.menu.MainMenu;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu(null);
            mainMenu.runGame();
        });
    }
}
