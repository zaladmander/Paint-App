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

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public void setOpacity(int opacity) {
        //TODO: limit max and min opacity
        this.opacity = opacity;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public void setSize(int size) {
        //TODO: limit max and min size
        this.size = size;
    }

    // REQUIRES: 0 <= red <= 255
    // MODIFIES: this
    // EFFECTS: set red to given int,
    //          only if given int in range
    public void setRed(int red) {
        if (red <= 255 && red >= 0) {
            this.red = red;
        }
    }

    // REQUIRES: 0 <= green <= 255
    // MODIFIES: this
    // EFFECTS: set green to given int,
    //          only if given int in range
    public void setGreen(int green) {
        if (green <= 255 && green >= 0) {
            this.green = green;
        }
    }

    // REQUIRES: 0 <= blue <= 255
    // MODIFIES: this
    // EFFECTS: set blue to given int,
    //          only if given int in range
    public void setBlue(int blue) {
        if (blue <= 255 && blue >= 0) {
            this.blue = blue;
        }
    }

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
