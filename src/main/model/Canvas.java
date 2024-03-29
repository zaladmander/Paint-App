package model;

import org.json.JSONObject;
import persistence.Writable;

// represents a canvas or layer which has a height, width, canvas type
// (photo or blank)
public class Canvas implements Writable {
    private static final int MINIMUM_WIDTH = 600;
    private static final int MINIMUM_HEIGHT = 600;
    private static final int MAXIMUM_WIDTH = 1800;
    private static final int MAXIMUM_HEIGHT = 1800;
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 800;

    private String type;
    private int height;
    private int width;

    // EFFECTS: constructs a canvas with type, height and width
    public Canvas(String type, int height, int width) {
        setType(type);
        setHeight(height);
        setWidth(width);
    }

    // setters

    // REQUIRES: type must be either "photo" or "blank"
    public void setType(String type) {
        if (type.equals("photo") || type.equals("blank")) {
            this.type = type;
        } else {
            this.type = "blank";
        }
    }

    // MODIFIES: width
    // EFFECTS: sets width to given width, only if width is within allowed range,
    //          otherwise, set width to default
    public void setWidth(int width) {
        if (width >= MINIMUM_WIDTH && width <= MAXIMUM_WIDTH) {
            this.width = width;
        } else {
            this.width = DEFAULT_WIDTH;
        }
    }

    // MODIFIES: height
    // EFFECTS: sets height to given height, only if height is within allowed range
    //          otherwise, set height to default
    public void setHeight(int height) {
        if (height >= MINIMUM_HEIGHT && height <= MAXIMUM_HEIGHT) {
            this.height = height;
        } else {
            this.height = DEFAULT_HEIGHT;
        }
    }

    // getters

    public String getType() {
        return type;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    //Referenced from JsonSerializationDemo
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: converts a Canvas into a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", type);
        json.put("height", height);
        json.put("width", width);
        return json;
    }
}
