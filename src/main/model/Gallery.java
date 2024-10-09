package model;

public class Gallery {

    // EFFECTS: creates empty collection, set current canvas index to 0
    public Gallery() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: returns the singleton instance of Gallery
    //          if the instance does not yet exist, creates it
    public static Gallery getInstance() {
        // stub
        return null;
    }

    // REQUIRES: width > 0 && height > 0
    // MODIFIES: this
    // EFFECTS: creates new canvas, adds it to the collection and 
    //          sets current canvas index to the index of the new canvas
    public void newCanvas(int width, int height) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds 1 to the current canvas index
    public void nextCanvas() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: subtracts 1 to the current canvas index
    public void prevCanvas() {
        // stub
    }

    // EFFECTS: returns the canvas at given index in the collection
    public Canvas getCanvas(int index) {
        // stub
        return null;

    }

    // EFFECTS: returns the canvas atthe current canvas index in the collection
    public Canvas getCurrCanvas() {
        // stub
        return null;
    }

    // MODIFIES: this
    // EFFECTS: sets current canvas index to 0
    public void resetCurrCanvas() {
        // stub
    }

    public int getCurrCanvasIndex() {
        // stub
        return 0;
    }
}