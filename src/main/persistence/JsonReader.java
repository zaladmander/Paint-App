package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

//Referenced from JsonSerializationDemo
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonReader {
    private final String source;
    private final BrushesRoom br = BrushesRoom.getBrushesRoom();

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads BrushesRoom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public void readBrushesRoom() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        addPencilCases(br, jsonObject);
    }

    // EFFECTS: reads DrawingRoom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public DrawingRoom readDrawingRoom() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDrawingRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses BrushesRoom from JSON object and returns it
    private void parseBrushesRoom(JSONObject jsonObject) {
        addPencilCases(br, jsonObject);
    }

    // EFFECTS: parses DrawingRoom from JSON object and returns it
    private DrawingRoom parseDrawingRoom(JSONObject jsonObject) {
        DrawingRoom dr = new DrawingRoom();
        addCanvases(dr, jsonObject);
        return dr;
    }

    // EFFECTS: reads canvases from JSONArray and adds to dr DrawingRoom
    private void addCanvases(DrawingRoom dr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("canvases");
        for (Object c : jsonArray) {
            JSONObject nextPencilCase = (JSONObject) c;
            addCanvas(dr, nextPencilCase);
        }
    }

    // EFFECTS: reads individual canvas from given JSONObject and parses into Canvas
    //          type, then adds the canvas to the given dr DrawingRoom
    private void addCanvas(DrawingRoom dr, JSONObject jsonObject) {
        String type = jsonObject.getString("type");
        int height = jsonObject.getInt("height");
        int width = jsonObject.getInt("width");
        Canvas c = new Canvas(type, height, width);
        dr.addCanvas(c);
    }

    // EFFECTS: gets array of cases from saved JSONArray and for each
    //          case in the array, parse the case
    private void addPencilCases(BrushesRoom br, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("cases");
        for (Object pc : jsonArray) {
            JSONObject nextPencilCase = (JSONObject) pc;
            addPencilCase(br, nextPencilCase);
        }
    }

    // EFFECTS: gets an individual pencilCase JSONObject and parses its
    //          fields, then calls helpers to parse the brushes, then adds
    //          the cases to the given BrushesRoom
    private void addPencilCase(BrushesRoom br, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        JSONArray brushes = jsonObject.getJSONArray("brushes");
        PencilCase pc = new PencilCase(name);
        addBrushes(pc, brushes);
        br.addPencilCase(pc);
    }

    // EFFECTS: gets JSONArray of brushes, and for each brush, parse
    //          the brush
    private void addBrushes(PencilCase pc, JSONArray brushes) {
        for (Object brush : brushes) {
            JSONObject nextBrush = (JSONObject) brush;
            addBrush(pc, nextBrush);
        }
    }

    // REQUIRES: given JSONObject has key values for same fields of a brush
    // MODIFIES: pc
    // EFFECTS: parse given JSONObject into a brush, then add the brush to
    //          the given PencilCase pc
    private void addBrush(PencilCase pc, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double opacity = jsonObject.getDouble("opacity");
        int size = jsonObject.getInt("size");
        int red = jsonObject.getInt("red");
        int green = jsonObject.getInt("green");
        int blue = jsonObject.getInt("blue");
        Brush brush = new Brush(size, name, red, green, blue, opacity);
        pc.addBrush(brush);
    }
}
