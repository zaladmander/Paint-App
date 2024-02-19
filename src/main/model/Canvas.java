package model;

// represents a canvas which has a height, width, canvas type
// (photo or blank)
public class Canvas {
    private String type;
    private int height;
    private int width;

    // EFFECTS: constructs a canvas with type, height and width
    public Canvas(String type, int height, int width) {
        setType(type);
        setHeight(height);
        setWidth(width);
    }

    // setters

    // REQUIRES: type must be either "photo" or "blank"
    public void setType(String type) {
        if (type.equals("photo") || type.equals("blank")) {
            this.type = type;
        } else {
            this.type = "blank";
        }
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // getters

    public String getType() {
        return type;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
