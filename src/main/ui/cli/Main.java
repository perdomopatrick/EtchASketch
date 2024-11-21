package ui.cli;

import ui.cli.menu.MainMenu;

public class Main {

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.menu();
    }
}
