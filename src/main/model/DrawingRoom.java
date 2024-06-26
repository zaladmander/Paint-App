package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// a single class instance that contains all canvases
// this class uses the singleton principle
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
        EventLogHelper.logEvent("DrawingRoom was reset!");
    }

    // MODIFIES: canvases
    // EFFECTS: adds the given canvas to the DrawingRoom cases list
    public void addCanvas(Canvas canvas) {
        canvases.add(canvas);
        EventLogHelper.logEvent(canvas.getType() + " " + canvas.getHeight() + "x" + canvas.getWidth()
                + " canvas was added to DrawingRoom.");
    }

    // REQUIRES: canvases not empty and position is a valid position in canvases
    // MODIFIES: canvases
    // EFFECTS: removes a canvas from position -1 (not zero based indexing)
    public void deleteCanvas(int position) {
        if (position > 0) {
            Canvas canvas = canvases.get(position - 1);
            EventLogHelper.logEvent(canvas.getType() + " " + canvas.getHeight() + "x" + canvas.getWidth()
                    + " canvas was deleted from DrawingRoom.");
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
