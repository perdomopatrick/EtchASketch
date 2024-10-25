package persistence;

import org.json.JSONObject;

// Writable interface
// Referenced from the JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}