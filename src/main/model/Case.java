package model;

import java.util.ArrayList;
import java.util.List;

// represents a case that can hold brushes of any type
public class Case {
    private List<Brush> brushes;

    public Case() {
        brushes = new ArrayList<>();
    }

    public void addBrush(Brush brush) {
        this.brushes.add(brush);
    }

    // REQUIRES: index in brushes list range and brushes must not be empty
    public void removeBrush(int index) {
        this.brushes.remove(index);
    }

    // REQUIRES: index is in brushes list range
    public Brush getBrush(int index) {
        return this.brushes.get(index);
    }

    public int getNumBrushes() {
        return this.brushes.size();
    }

    public List<Brush> getBrushes() {
        return brushes;
    }
}
