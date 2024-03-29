package ui;

import model.BrushesRoom;
import model.DrawingRoom;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

// represents a singular file helper that can be invoked to save and load
public class FileHelper {
    public static final String JSON_STORE_BR = "./data/brushesroom.json";
    public static final String JSON_STORE_DR = "./data/drawingroom.json";
    private static FileHelper fileHelper;

    private final BrushesRoom brushroom = BrushesRoom.getBrushesRoom();
    private final DrawingRoom drawroom = DrawingRoom.getDrawingRoom();
    private final JsonWriter jsonWriterBrushesRoom;
    private final JsonReader jsonReaderBrushesRoom;
    private final JsonWriter jsonWriterDrawingRoom;
    private final JsonReader jsonReaderDrawingRoom;

    // EFFECTS: construct a file helper
    private FileHelper() {
        jsonWriterBrushesRoom = new JsonWriter(JSON_STORE_BR);
        jsonReaderBrushesRoom = new JsonReader(JSON_STORE_BR);
        jsonWriterDrawingRoom = new JsonWriter(JSON_STORE_DR);
        jsonReaderDrawingRoom = new JsonReader(JSON_STORE_DR);
    }

    // EFFECTS: returns the single instance of fileHelper
    public static FileHelper getFileHelper() {
        if (fileHelper == null) {
            fileHelper = new FileHelper();
        }
        return fileHelper;
    }

    // EFFECTS: saves brushes and drawing room to file
    public void saveAll() {
        saveBrushesRoom();
        saveDrawingRoom();
        System.out.println("Saving Complete!");
    }

    // EFFECTS: loads all brushes and drawing room from file
    public void loadAll() {
        loadBrushesRoom();
        loadDrawingRoom();
        System.out.println("Loading Complete!");
    }

    // EFFECTS: saves the current brushes room to file
    public void saveBrushesRoom() {
        try {
            jsonWriterBrushesRoom.open();
            jsonWriterBrushesRoom.writeBrushRoom(brushroom);
            jsonWriterBrushesRoom.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_BR);
        }
    }

    // EFFECTS: loads the brush room from save
    public void loadBrushesRoom() {
        brushroom.reset();
        try {
            jsonReaderBrushesRoom.readBrushesRoom();
        } catch (IOException e) {
            System.out.println("Unable to load file: " + JSON_STORE_BR);
        }
    }

    // EFFECTS: saves the current drawing room to file
    public void saveDrawingRoom() {
        try {
            jsonWriterDrawingRoom.open();
            jsonWriterDrawingRoom.writeDrawingRoom(drawroom);
            jsonWriterDrawingRoom.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_DR);
        }
    }

    // EFFECTS: loads the drawing room from save
    public void loadDrawingRoom() {
        drawroom.reset();
        try {
            jsonReaderDrawingRoom.readDrawingRoom();
        } catch (IOException e) {
            System.out.println("Unable to load file: " + JSON_STORE_DR);
        }
    }

    // MODIFIES: drawroom, brushroom
    // EFFECTS: resets the canvases and cases contained within brushroom
    //          and drawroom, then saves and loads them
    public void reset() {
        brushroom.reset();
        saveBrushesRoom();
        drawroom.reset();
        saveDrawingRoom();
        loadBrushesRoom();
        loadDrawingRoom();
    }
}
