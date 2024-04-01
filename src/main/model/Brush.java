package model;

import org.json.JSONObject;
import persistence.Writable;

// represents a default brush with RGB values, a brush size,
// opacity, and a name. All can be changed with setters
public class Brush implements Writable {
    private String name;
    //TODO: change opacity to int, use [0,100] or something
    private double opacity;
    private int size;
    private int red;
    private int green;
    private int blue;

    // EFFECTS: constructs a Paintbrush
    public Brush(int size, String name, int red, int green, int blue, double opacity) {
        setOpacity(opacity);
        setSize(size);
        setName(name);
        setRed(red);
        setGreen(green);
        setBlue(blue);
    }

    // setters

    // REQUIRES: 0 <= opacity <= 1
    // MODIFIES: this
    // EFFECTS: set opacity to given double
    public void setOpacity(double opacity) {
        if (opacity > 1) {
            this.opacity = 1;
        } else if (opacity < 0) {
            this.opacity = 0;
        } else {
            this.opacity = opacity;
        }
        EventLogHelper.logEvent("Brush '" + getName() + "': Opacity set to " + this.opacity);
    }

    // REQUIRES: size >= 1
    // MODIFIES: this
    // EFFECTS: sets size to given int, if < 1,
    //          set to 1
    public void setSize(int size) {
        this.size = Math.max(size, 1);
        EventLogHelper.logEvent("Brush '" + getName() + "': Size set to " + this.size);
    }

    // MODIFIES: this
    // EFFECTS: set red to given int unless,
    //          if given int > 255, set red to 255
    //          if given int < 0, set red to 0
    public void setRed(int red) {
        if (red > 255) {
            this.red = 255;
        } else {
            this.red = Math.max(red, 0);
        }
        EventLogHelper.logEvent("Brush '" + getName() + "': Red set to " + this.red);
    }

    // MODIFIES: this
    // EFFECTS: set green to given int unless,
    //          if given int > 255, set green to 255
    //          if given int < 0, set green to 0
    public void setGreen(int green) {
        if (green > 255) {
            this.green = 255;
        } else {
            this.green = Math.max(green, 0);
        }
        EventLogHelper.logEvent("Brush '" + getName() + "': Green set to " + this.green);
    }

    // MODIFIES: this
    // EFFECTS: set blue to given int unless,
    //          if given int > 255, set blue to 255
    //          if given int < 0, set blue to 0
    public void setBlue(int blue) {
        if (blue > 255) {
            this.blue = 255;
        } else {
            this.blue = Math.max(blue, 0);
        }
        EventLogHelper.logEvent("Brush '" + getName() + "': Blue set to " + this.blue);
    }

    // EFFECTS: sets name to given name if string at least one letter length
    public void setName(String name) {
        if (name.length() >= 1) {
            EventLogHelper.logEvent("Brush '" + this.name + "': Name set to " + name);
            this.name = name;
        }
    }

    // getters
    public double getOpacity() {
        return opacity;
    }

    public int getSize() {
        return size;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public String getName() {
        return name;
    }

    //Referenced from JsonSerializationDemo
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    //EFFECTS: converts a Brush into a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("opacity", opacity);
        json.put("size", size);
        json.put("red", red);
        json.put("green", green);
        json.put("blue", blue);
        return json;
    }
}
