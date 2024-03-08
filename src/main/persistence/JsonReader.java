package persistence;

import model.Brush;
import model.BrushesRoom;
import model.PencilCase;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//Referenced from JsonSerializationDemo
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads BrushesRoom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BrushesRoom readBrushesRoom() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBrushesRoom(jsonObject);
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
    private BrushesRoom parseBrushesRoom(JSONObject jsonObject) {
        BrushesRoom br = new BrushesRoom();
        addPencilCases(br, jsonObject);
        return br;
    }

    private void addPencilCases(BrushesRoom br, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("cases");
    }

    private void addPencilCase(BrushesRoom br, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
    }

    private void addBrushes(BrushesRoom br, JSONObject jsonObject) {

    }

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

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addCases(BrushesRoom br, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("cases");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addThingy(br, nextThingy);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addThingy(BrushesRoom wr, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        //Category category = Category.valueOf(jsonObject.getString("category"));
        //Thingy thingy = new Thingy(name, category);
        //wr.addThingy(thingy);
    }
}
