package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represents a PencilCase that can hold brushes of any type, has a
// list of brushes, and a case name, brushes can be added and removed,
// case name can be changed
public class PencilCase implements Writable {
    private List<Brush> brushes;
    private String name;

    // EFFECTS: constructs a case with empty list brushes and a name
    public PencilCase(String name) {
        brushes = new ArrayList<>();
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: adds given brush to the brushes list
    public void addBrush(Brush brush) {
        this.brushes.add(brush);
    }

    // REQUIRES: index in brushes list range and brushes must not be empty
    // MODIFIES: this
    // EFFECTS: removes brush at index from brushes
    public void removeBrush(int index) {
        this.brushes.remove(index);
    }

    // setters

    // MODIFIES: this
    // EFFECTS: sets name to given name
    public void setName(String name) {
        this.name = name;
    }

    // getters

    // REQUIRES: index is in brushes list range
    // EFFECTS: returns brush at index from brushes list
    public Brush getBrush(int index) {
        return this.brushes.get(index);
    }

    // EFFECTS: with given string, returns brush with same name from brushes
    //          if brushes is empty or there is no brush with given name,
    //          return null
    public Brush getBrushWithName(String name) {
        for (Brush b: brushes) {
            if (b.getName().equals(name)) {
                return b;
            }
        }
        return null;
    }

    public int getNumBrushes() {
        return this.brushes.size();
    }

    public List<Brush> getBrushes() {
        return brushes;
    }

    public String getName() {
        return name;
    }

    //Referenced from JsonSerializationDemo
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // converts a PencilCase into a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("brushes", brushesToJson());
        return json;
    }

    // EFFECTS: converts and returns brushes to Json Array
    private JSONArray brushesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Brush b : brushes) {
            jsonArray.put(b.toJson());
        }
        return jsonArray;
    }
}
