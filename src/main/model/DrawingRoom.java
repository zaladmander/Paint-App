package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// a single class instance that contains all canvases
public class DrawingRoom implements Writable {
    private static DrawingRoom drawingRoom;
    private List<Canvas> canvases;

    // EFFECTS: constructs a DrawingRoom with an empty list canvases
    private DrawingRoom() {
        canvases = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: resets all fields
    public void reset() {
        canvases = new ArrayList<>();
    }

    // MODIFIES: cases
    // EFFECTS: adds the given canvas to the DrawingRoom cases list
    public void addCanvas(Canvas canvas) {
        canvases.add(canvas);
    }

    // REQUIRES: canvases not empty and position is a valid position in canvases
    // MODIFIES: canvases
    // EFFECTS: removes a canvas from position -1
    public void deleteCanvas(int position) {
        if (position > 0) {
            canvases.remove(position - 1);
        }
    }

    // getters:

    public List<Canvas> getCanvases() {
        return canvases;
    }

    public static DrawingRoom getDrawingRoom() {
        if (drawingRoom == null) {
            drawingRoom = new DrawingRoom();
        }
        return drawingRoom;
    }

    //Referenced from JsonSerializationDemo
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    //EFFECTS: converts a DrawingRoom into a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("canvases", canvasesToJson());
        return json;
    }

    // EFFECTS: converts and returns canvases into Json Array
    private JSONArray canvasesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Canvas c : canvases) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }
}
