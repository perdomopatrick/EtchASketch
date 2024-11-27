package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a Gallery having the list of board and the current index in view
public class Gallery implements Writable {

    private static Gallery instance;
    private ArrayList<Canvas> collection;
    private int currCanvasIndex;

    // EFFECTS: creates empty collection, set current canvas index to 0
    private Gallery() {
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

    // EFFECTS: clear collection and set current canvas index to 0
    public void clear() {
        collection = new ArrayList<>();
        currCanvasIndex = 0;    
    }

    // REQUIRES: width > 0 && height > 0
    // MODIFIES: this
    // EFFECTS: creates new canvas, adds it to the collection and
    // sets current canvas index to the index of the new canvas
    public void newCanvas(int width, int height) {
        Canvas newCanvas = new Canvas(width, height);
        collection.add(newCanvas);
        currCanvasIndex = collection.indexOf(newCanvas);
        EventLog.getInstance().logEvent(new Event("Added new " + width + "x" + height + " canvas to gallery"));

    }

    // MODIFIES: this
    // EFFECTS: adds 1 to the current canvas index
    // if it does not exceed the size of the collection
    public void nextCanvas() {
        if (currCanvasIndex++ >= collection.size()) {
            currCanvasIndex--;
        }
        EventLog.getInstance().logEvent(new Event("Added one from current canvas index"));
    }

    // MODIFIES: this
    // EFFECTS: subtracts 1 to the current canvas index
    // if it does not go below 0
    public void prevCanvas() {
        if (currCanvasIndex-- < 0) {
            currCanvasIndex++;
        }
        EventLog.getInstance().logEvent(new Event("Subtracted one from current canvas index"));
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
        EventLog.getInstance().logEvent(new Event("Reset current canvas index to zero"));
    }

    // MODIFIES: this
    // EFFECTS: adds given canvas to collection
    public void addCanvas(Canvas canvas) {
        collection.add(canvas);
        EventLog.getInstance().logEvent(new Event("Added given canvas to gallery"));
    }

    public int getCurrCanvasIndex() {
        return currCanvasIndex;
    }

    // EFFECTS: returns a JSONObject containing the canvases in JSON format
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("canvases", canvasesToJson());
        return json;
    }

    // EFFECTS: returns a JSONArray of JSON representation of each Canvas in the
    // collection
    private JSONArray canvasesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Canvas c : collection) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }
}