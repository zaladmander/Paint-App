package model;

import java.util.ArrayList;
import java.util.List;

// represents a case that can hold brushes of any type
public class Case {
    private List<Brush> brushes;
    private String name;

    public Case(String name) {
        brushes = new ArrayList<>();
        this.name = name;
    }

    public void addBrush(Brush brush) {
        this.brushes.add(brush);
    }

    // REQUIRES: index in brushes list range and brushes must not be empty
    public void removeBrush(int index) {
        this.brushes.remove(index);
    }

    public void removeBrushWithName(String name) {
        for (Brush b: brushes) {
            if (b.getName().equals(name)) {
                brushes.remove(b);
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    // REQUIRES: index is in brushes list range
    public Brush getBrush(int index) {
        return this.brushes.get(index);
    }

    // REQUIRES: // TODO i forgor
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
}
