package model;

import java.util.ArrayList;
import java.util.List;

// represents a default brush with RGB values, a brush size,
// opacity, texture
public class Brush {
    private double opacity;
    private int size;
    private int red;
    private int green;
    private int blue;
    private String texture;

    // EFFECTS: constructs a Paintbrush
    public Brush(int size) {
        this.opacity = 1;
        this.size = size;
        this.texture = "default";
    }

    // setters

    // REQUIRES: 0 <= opacity <= 1
    // MODIFIES: this
    // EFFECTS: set opacity to given double
    public void setOpacity(double opacity) {
        if (opacity > 1) {
            this.opacity = 1;
        } else if (opacity < 0) {
            this.opacity = 0;
        } else {
            this.opacity = opacity;
        }
    }

    // REQUIRES: size >= 1
    // MODIFIES: this
    // EFFECTS: sets size to given int, if < 1,
    //          set to 1
    public void setSize(int size) {
        this.size = Math.max(size, 1);
    }

    // MODIFIES: this
    // EFFECTS: set red to given int unless,
    //          if given int > 255, set red to 255
    //          if given int < 0, set red to 0
    public void setRed(int red) {
        if (red > 255) {
            this.red = 255;
        } else {
            this.red = Math.max(red, 0);
        }
    }

    // MODIFIES: this
    // EFFECTS: set green to given int unless,
    //          if given int > 255, set green to 255
    //          if given int < 0, set green to 0
    public void setGreen(int green) {
        if (green > 255) {
            this.green = 255;
        } else {
            this.green = Math.max(green, 0);
        }
    }

    // MODIFIES: this
    // EFFECTS: set blue to given int unless,
    //          if given int > 255, set blue to 255
    //          if given int < 0, set blue to 0
    public void setBlue(int blue) {
        if (blue > 255) {
            this.blue = 255;
        } else {
            this.blue = Math.max(blue, 0);
        }
    }

    // REQUIRES: given texture is valid
    // EFFECTS: set texture to given texture
    //          only if it's a valid texture
    public void setTexture(String texture) {
        List<String> validTextures = new ArrayList<>();
        for (String s : validTextures) {
            if (texture == s) {
                this.texture = texture;
                break;
            }
        }
    }

    // getters
    public double getOpacity() {
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
