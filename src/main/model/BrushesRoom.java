package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// a single class instance that contains all PencilCases with their respective brushes
// TODO: this class will use the singleton principle
public class BrushesRoom implements Writable {
    private List<PencilCase> cases;
    private static BrushesRoom brushesRoom;

    // EFFECTS: constructs a BrushesRoom with an empty cases list
    private BrushesRoom() {
        cases = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: resets everything about this BrushesRoom
    public void reset() {
        cases = new ArrayList<>();
    }

    // MODIFIES: cases
    // EFFECTS: deletes a case with same string name as caseToDelete
    public void deleteCase(String caseToDelete) {
        cases.remove(getCaseWithName(caseToDelete));
    }

    // MODIFIES: pencilCase
    // EFFECTS: deletes a brush with same string name as brushToDelete
    public void deleteBrush(String brushToDelete, PencilCase pencilCase) {
        Brush brush = pencilCase.getBrushWithName(brushToDelete);
        pencilCase.getBrushes().remove(brush);
    }

    // EFFECTS: return true if name is in the brushes list, false otherwise
    public boolean isNameInListBrushes(String name, PencilCase pencilCase) {
        for (Brush b : pencilCase.getBrushes()) {
            if (b.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: return true if name is in the cases list, false otherwise
    public boolean isNameInListCases(String name) {
        for (PencilCase c : cases) {
            if (c.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: return case with given name if case in the cases list, null otherwise
    public PencilCase getCaseWithName(String name) {
        for (PencilCase c : cases) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    // MODIFIES: cases
    // EFFECTS: adds the given pencilCase to the BrushesRoom cases list
    public void addPencilCase(PencilCase pc) {
        cases.add(pc);
    }

    public List<PencilCase> getCases() {
        return cases;
    }

    public static BrushesRoom getBrushesRoom() {
        if (brushesRoom == null) {
            brushesRoom = new BrushesRoom();
        }
        return brushesRoom;
    }

    //Referenced from JsonSerializationDemo
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: converts a PencilCase into a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("cases", casesToJson());
        return json;
    }

    // EFFECTS: converts and returns cases into Json Array format
    private JSONArray casesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (PencilCase c : cases) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }
}
