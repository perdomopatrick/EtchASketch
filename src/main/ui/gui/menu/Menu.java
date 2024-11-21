package ui.gui.menu;

import model.Gallery;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

// abstract Menu class
public abstract class Menu {

    protected Gallery gallery;
    protected final MainMenu mainMenu;

    public Menu(MainMenu main) {
        this.gallery = Gallery.getInstance();
        this.mainMenu = main;
    }

    // EFFECTS: return the JPanel of the menu
    public abstract JPanel menu();

    // EFFECTS: Displays the canvas on g
    protected void displayCanvas(boolean[][] b, Graphics g, int cellWidth, int cellHeight) {
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                g.setColor(b[i][j] ? Color.BLACK : Color.WHITE);

                g.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
                g.drawRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
            }
        }
    }

    // EFFECTS: Displays the canvas with stylus on g
    protected void displayCanvasWithStylus(boolean[][] b, Graphics g, int stylusXCoord, int stylusYCoord, int cellWidth,
            int cellHeight) {
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                if (i == stylusYCoord && j == stylusXCoord) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(b[i][j] ? Color.BLACK : Color.WHITE);
                }
                g.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
                g.drawRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
            }
        }
    }

}
