package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

import model.Canvas;
import model.Gallery;


// Represents a reader that reads gallery from JSON data stored in file
// Referenced from the JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads gallery from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Gallery read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGallery(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {

        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses JSON object to get and returns gallery
    private Gallery parseGallery(JSONObject jsonObject) {
        Gallery g = new Gallery();
        addCanvases(g, jsonObject);
        return g;
    }

    // MODIFIES: g
    // EFFECTS: parses JSON object to get and add canvases to gallery
    private void addCanvases(Gallery g, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("canvases");
        for (Object json : jsonArray) {
            JSONObject nextCanvas = (JSONObject) json;
            addCanvas(g, nextCanvas);
        }
    }

    // MODIFIES: g
    // EFFECTS: parses JSON object to get and add canvas to gallery
    private void addCanvas(Gallery g, JSONObject jsonObject) {
        int stylusXCoord = jsonObject.getInt("stylusXCoord");
        int stylusYCoord = jsonObject.getInt("stylusYCoord");

        JSONArray jsonArray = jsonObject.getJSONArray("canvas");

        boolean[][] board = new boolean[jsonArray.length()][jsonArray.getJSONArray(0).length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONArray innerArray = jsonArray.getJSONArray(i);
            for (int j = 0; j < innerArray.length(); j++) {
                board[i][j] = innerArray.getBoolean(j);
            }
        }
        Canvas canvas = new Canvas(board, stylusXCoord, stylusYCoord);
        g.addCanvas(canvas);
    }
}
