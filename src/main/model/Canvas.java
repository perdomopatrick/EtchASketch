package model;

// Represents a canvas having the board, stylus coordinates and dimensions
public class Canvas {

    private boolean[][] board;
    private int stylusXCoord;
    private int stylusYCoord;
    private final int height;
    private final int width;

    // REQUIRES: width > 0 && height > 0
    // EFFECTS: creates board with dimensions given width and height,
    //          set dimensions to width and height and
    //          set stylus coordinates to (0, 0)
    public Canvas(int width, int height) {
        board = new boolean[height][width];
        this.width = width;
        this.height = height;
        setStylusXCoord(0);
        setStylusYCoord(0);
    }

    // REQUIRES: move is valid
    //          (direction == right || direction == left ||
    //           direction == up || direction == down) &&
    //           length stays inbounds
    // MODIFIES: this
    // EFFECTS: check for direction and draws in that direction by length amount
    public void draw(String direction, int length) {
        if (direction.equals("up")) {
            colourColumn(stylusYCoord - length, stylusYCoord - 1);
            setStylusYCoord(stylusYCoord - length);
        } else if (direction.equals("down")) {
            colourColumn(stylusYCoord + 1, stylusYCoord + length);
            setStylusYCoord(stylusYCoord + length);
        } else if (direction.equals("left")) {
            colourRow(stylusXCoord - length, stylusXCoord - 1);
            setStylusXCoord(stylusXCoord - length);
        } else { // right
            colourRow(stylusXCoord + 1, stylusXCoord + length);
            setStylusXCoord(stylusXCoord + length);
        }
    }

    // REQUIRES: colStart <= colEnd
    // MODIFIES: this
    // EFFECTS: Sets all elements in row stylus y between colStart and colEnd
    // (inclusive) to true
    private void colourRow(int colStart, int colEnd) {
        for (int i = colStart; i <= colEnd; i++) {
            board[stylusYCoord][i] = true;
        }
    }

    // REQUIRES: rowStart <= rowEnd
    // MODIFIES: this
    // EFFECTS: Sets all elements in column stylus x between rowStart and rowEnd
    // (inclusive) to true
    private void colourColumn(int rowStart, int rowEnd) {
        for (int i = rowStart; i <= rowEnd; i++) {
            board[i][stylusXCoord] = true;
        }
    }

    // MODIFIES: this
    // EFFECTS: Reset the board to be empty
    public void shake() {
        board = new boolean[height][width];
    }

    public boolean[][] getBoard() {
        return board;
    }

    protected void setStylusXCoord(int stylusXCoord) {
        this.stylusXCoord = stylusXCoord;
    }

    protected void setStylusYCoord(int stylusYCoord) {
        this.stylusYCoord = stylusYCoord;
    }

    public int getStylusXCoord() {
        return stylusXCoord;
    }

    public int getStylusYCoord() {
        return stylusYCoord;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
