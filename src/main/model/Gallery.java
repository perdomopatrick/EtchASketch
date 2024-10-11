package model;

import java.util.ArrayList;

public class Gallery {

    private static Gallery instance;
    private final ArrayList<Canvas> collection;
    private int currCanvasIndex;

    // EFFECTS: creates empty collection, set current canvas index to 0
    public Gallery() {
        collection = new ArrayList<>();
        currCanvasIndex = 0;
    }

    // MODIFIES: this
    // EFFECTS: returns the singleton instance of Gallery
    // if the instance does not yet exist, creates it
    public static Gallery getInstance() {
        if (instance == null) {
            instance = new Gallery();
        }
        return instance;
    }

    // REQUIRES: width > 0 && height > 0
    // MODIFIES: this
    // EFFECTS: creates new canvas, adds it to the collection and
    // sets current canvas index to the index of the new canvas
    public void newCanvas(int width, int height) {
        Canvas newCanvas = new Canvas(width, height);
        collection.add(newCanvas);
        currCanvasIndex = collection.indexOf(newCanvas);
    }

    // MODIFIES: this
    // EFFECTS: adds 1 to the current canvas index
    // if it does not exceed the size of the collection
    public void nextCanvas() {
    	currCanvasIndex++;
    }

    // MODIFIES: this
    // EFFECTS: subtracts 1 to the current canvas index
    // if it does not go below 0
    public void prevCanvas() {
    	currCanvasIndex--;
    }

    // EFFECTS: returns the canvas at given index in the collection
    public Canvas getCanvas(int index) {
        return collection.get(index);
    }

    // Requires: currCanvasIndex element to exist in the collection
    // EFFECTS: returns the canvas atthe current canvas index in the collection
    public Canvas getCurrCanvas() {
        return collection.get(currCanvasIndex);
    }

    // MODIFIES: this
    // EFFECTS: sets current canvas index to 0
    public void resetCurrCanvas() {
        currCanvasIndex = 0;
    }

    public int getCurrCanvasIndex() {
        return currCanvasIndex;
    }
}