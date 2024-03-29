package model;

import java.awt.*;

// a class that represents a drawn dot on the screen, with a color, x, and y coordinate
public class Dot {
    private Color color;
    private int xcoord;
    private int ycoord;

    // MODIFIES: color, xcoord, ycoord
    // EFFECTS: constructs a Dot
    public Dot(Color color, int xcoord, int ycoord) {
        this.color = color;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
    }

    // getters:

    public Color getColor() {
        return color;
    }

    public int getXcoord() {
        return xcoord;
    }

    public int getYcoord() {
        return ycoord;
    }
}
