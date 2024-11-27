package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents a canvas having the board, stylus coordinates and dimensions
public class Canvas implements Writable {

    private boolean[][] board;
    private int stylusXCoord;
    private int stylusYCoord;
    private final int height;
    private final int width;

    // REQUIRES: width > 0 && height > 0
    // EFFECTS: creates board with dimensions given width and height,
    // set dimensions to width and height and
    // set stylus coordinates to (0, 0)
    public Canvas(int width, int height) {
        board = new boolean[height][width];
        this.width = width;
        this.height = height;
        EventLog.getInstance().logEvent(new Event("New " + width + "x" + height + " canvas"));
        setStylusXCoord(0);
        setStylusYCoord(0);
    }

    // REQUIRES: stylusXCoord > 0 && stylusYCoord > 0 && board != null
    // EFFECTS: copies board, set stylus coordinates to given
    public Canvas(boolean[][] board, int stylusXCoord, int stylusYCoord) {
        this.board = board;
        height = board.length;
        width = board[0].length;

        EventLog.getInstance().logEvent(new Event("Created loaded canvas"));
        setStylusXCoord(stylusXCoord);
        setStylusYCoord(stylusYCoord);
    }

    // REQUIRES: move is valid
    // (direction == right || direction == left ||
    // direction == up || direction == down) &&
    // length >= 0
    // MODIFIES: this
    // EFFECTS: check for direction and draws in that direction by length amount
    // if invailf move like goes out of bounds returns false otherwise true
    public boolean draw(String direction, int length) {
        try {
            if (direction.equals("up")) {
                colourColumn(stylusYCoord - 1, stylusYCoord - length, -1);
            } else if (direction.equals("down")) {
                colourColumn(stylusYCoord + 1, stylusYCoord + length, 1);
            } else if (direction.equals("left")) {
                colourRow(stylusXCoord - 1, stylusXCoord - length, -1);
            } else if (direction.equals("right")) {
                colourRow(stylusXCoord + 1, stylusXCoord + length, 1);
            } else {
                return false;
            }
            EventLog.getInstance().logEvent(new Event("Drew " + direction + " by " + length));
        } catch (IndexOutOfBoundsException e) {
            EventLog.getInstance().logEvent(new Event("Drew " + direction + " by " + length + " (Hit the border)"));
            return false;
        }
        return true;
    }

    // REQUIRES: d == 1 || d == -1
    // MODIFIES: this
    // EFFECTS: Sets all elements in row stylus y between colStart and colEnd
    // (inclusive) to true, d is the direction negative (left), positive (right)
    private void colourRow(int colStart, int colEnd, int d) {
        for (int i = colStart; (d > 0 ? i <= colEnd : i >= colEnd); i += d) {
            board[stylusYCoord][i] = true;
            setStylusXCoord(i);
        }
    }

    // REQUIRES: d == 1 || d == -1
    // MODIFIES: this
    // EFFECTS: Sets all elements in column stylus x between rowStart and rowEnd
    // (inclusive) to true, d is the direction negative (up), positive (down)
    private void colourColumn(int rowStart, int rowEnd, int d) {
        for (int i = rowStart; (d > 0 ? i <= rowEnd : i >= rowEnd); i += d) {
            board[i][stylusXCoord] = true;
            setStylusYCoord(i);
        }
    }

    // MODIFIES: this
    // EFFECTS: Reset the board to be empty
    public void shake() {
        board = new boolean[height][width];
        EventLog.getInstance().logEvent(new Event("Shaked canvas"));
    }

    public boolean[][] getBoard() {
        return board;
    }

    protected void setStylusXCoord(int stylusXCoord) {
        this.stylusXCoord = stylusXCoord;
        EventLog.getInstance().logEvent(new Event("Set stylus X coordinate to " + stylusXCoord));
    }

    protected void setStylusYCoord(int stylusYCoord) {
        this.stylusYCoord = stylusYCoord;
        EventLog.getInstance().logEvent(new Event("Set stylus Y coordinate to " + stylusYCoord));
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

    // EFFECTS: returns a JSONObject containing the canvases in JSON format
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("stylusXCoord", stylusXCoord);
        json.put("stylusYCoord", stylusYCoord);
        json.put("canvas", board);

        return json;
    }
}