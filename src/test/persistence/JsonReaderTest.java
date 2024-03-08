package persistence;

import model.BrushesRoom;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testSourceNotFound() {
        JsonReader jsonReader = new JsonReader("this//:aint:a..source:playboicarti");
        try {
            BrushesRoom brushroom = jsonReader.readBrushesRoom();
            fail("No exceptions thrown :( ");
        } catch (IOException e) {
            // good stuff
        }
    }

    @Test
    void testReaderEmptyBrushesRoom() {
        JsonReader jsonReader = new JsonReader("./data/testReaderEmptyBrushesRoom.json");
        try {
            BrushesRoom br = jsonReader.readBrushesRoom();
            assertEquals(0, br.getCases().size());
        } catch (IOException e) {
            fail("Didn't expect an exception");
        }
    }
}
