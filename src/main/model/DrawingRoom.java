package model;

import java.util.ArrayList;
import java.util.List;

// a class that contains all canvases
public class DrawingRoom {
    private List<Canvas> canvases;

    public DrawingRoom() {
        canvases = new ArrayList<>();
    }

    // REQUIRES: canvases not empty and index is a valid position in canvases
    // MODIFIES: canvases
    // EFFECTS: removes a canvas from index -1
    public void deleteCanvas(int index) {
        if (index > 0) {
            canvases.remove(index - 1);
        }
    }

    public List<Canvas> getCanvases() {
        return canvases;
    }
}
