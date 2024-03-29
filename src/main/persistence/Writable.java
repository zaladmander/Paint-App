package persistence;

import org.json.JSONObject;

//Referenced from JsonSerializationDemo
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// an interface that represents a class that can be saved and loaded to file
public interface Writable {

    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
