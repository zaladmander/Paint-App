package model;

import java.awt.*;

// TODO: add tests and documentation
public class Dot {
    private Color color;
    private int xcoord;
    private int ycoord;

    public Dot(Color color, int xcoord, int ycoord) {
        this.color = color;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
    }

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
