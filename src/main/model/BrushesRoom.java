package model;

import java.util.ArrayList;
import java.util.List;

// a class that contains all PencilCases with their respective brushes
public class BrushesRoom {
    private List<PencilCase> cases;

    public BrushesRoom() {
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

    public List<PencilCase> getCases() {
        return cases;
    }
}
