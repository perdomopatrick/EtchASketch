package persistence;

//import model.Category;
//import model.Thingy;
//import model.WorkRoom;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

import model.Canvas;
import model.Gallery;

// Represents a reader that reads gallery from JSON data stored in file
public class JsonReader {

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
    }

    // EFFECTS: reads gallery from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Gallery read() throws IOException {
        return null; // stub
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return null; // stub
    }

    // EFFECTS: parses gallery from JSON object and returns it
    private Gallery parseGallery(JSONObject jsonObject) {
        return null;// stub
    }

    // MODIFIES: g
    // EFFECTS: parses Canvases from JSON object and adds them to gallery
    private void addCanvases(Gallery g, JSONObject jsonObject) {
        // stub
    }

    // MODIFIES: g
    // EFFECTS: parses Canvas from JSON object and adds it to gallery
    private void addCanvas(Gallery g, JSONObject jsonObject) {
        // stub
    }
}
