package model;

import java.util.ArrayList;
import java.util.List;

// represents a default brush with RGB values, a brush size,
// opacity, texture
public class Brush {
    private int opacity;
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

    // MODIFIES: this
    // EFFECTS: set red to given int unless,
    //          if given int > 255, set red to 255
    //          if given int < 0, set red to 0
    public void setRed(int red) {
        if (red <= 255 && red >= 0) {
            this.red = red;
        } else if (red > 255) {
            this.red = 255;
        } else if (red < 0) {
            this.red = 0;
        }
    }

    // MODIFIES: this
    // EFFECTS: set green to given int unless,
    //          if given int > 255, set green to 255
    //          if given int < 0, set green to 0
    public void setGreen(int green) {
        if (green <= 255 && green >= 0) {
            this.green = green;
        } else if (green > 255) {
            this.green = 255;
        } else if (green < 0) {
            this.green = 0;
        }
    }

    // MODIFIES: this
    // EFFECTS: set blue to given int unless,
    //          if given int > 255, set blue to 255
    //          if given int < 0, set blue to 0
    public void setBlue(int blue) {
        if (blue <= 255 && blue >= 0) {
            this.blue = blue;
        } else if (blue > 255) {
            this.blue = 255;
        } else if (blue < 0) {
            this.blue = 0;
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
            }
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
