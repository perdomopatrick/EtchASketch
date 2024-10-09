package model;

// Represents a canvas having the board, stylus coordinates and dimensions
public class Canvas {

    // REQUIRES: width > 0 && height > 0
    // EFFECTS: creates board with dimensions given width and height, 
    //          set dimensions to width and height and 
    //          set stylus coordinates to (0, 0)
    public Canvas(int width, int height) {
        // stub
    }

    // REQUIRES: move is valid 
    //           (direction == right || direction == left || 
    //            direction == up || direction == down) &&
    //           lenght stays inbounds
    // MODIFIES: this
    // EFFECTS: check for direction and draws in that direction by length amount
    public void draw(String direction, int length) {
        // stub
    }

    // REQUIRES: colStart <= colEnd
    // MODIFIES: this
    // EFFECTS: Sets all elements in row stylus y between colStart and colEnd (inclusive) to true
    private void colourRow(int colStart, int colEnd) {
        // stub
    }

    // REQUIRES: rowStart <= rowEnd
    // MODIFIES: this
    // EFFECTS: Sets all elements in column stylus x between rowStart and rowEnd (inclusive) to true
    private void colourColumn(int rowStart, int rowEnd) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: Reset the board to be empty
    public void shake() {
        // stub
    }

    public boolean[][] getBoard() {
        // stub
		return null;
	}

	protected void setStylusXCoord(int stylusXCoord) {
        // stub
	}

    public int getStylusXCoord() {
        // stub
		return 0;
	}

	protected void setStylusYCoord(int stylusYCoord) {
        // stub
	}

	public int getStylusYCoord() {
        // stub
		return 0;
	}

    public int getHeight() {
        // stub
		return 0;
	}
	
	public int getWidth() {
        // stub
		return 0;
	}

}
