package model;

// represents a default brush with RGB values, a brush size,
// opacity, texture
public class Brush {
    private int opacity;
    private int size;
    private int red;
    private int green;
    private int blue;

    // EFFECTS: constructs a Paintbrush
    public Brush(int opacity, int size) {
        this.opacity = opacity;
        this.size = size;
    }

    // setters

    // getters
    public int getOpacity() {
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

}
