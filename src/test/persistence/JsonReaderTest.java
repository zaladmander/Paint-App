package persistence;

import model.Brush;
import model.BrushesRoom;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest{

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

    @Test
    void testReaderCasesNoBrushes() {
        JsonReader jsonReader = new JsonReader("./data/testReaderCasesNoBrushes.json");
        try {
            BrushesRoom br = jsonReader.readBrushesRoom();
            assertEquals(2, br.getCases().size());
            assertEquals("sponges", br.getCases().get(0).getName());
            assertTrue(br.getCases().get(0).getBrushes().isEmpty());
            assertEquals("felts", br.getCases().get(1).getName());
            assertTrue(br.getCases().get(1).getBrushes().isEmpty());
        } catch (IOException e) {
            fail("Didn't expect an exception");
        }
    }

    @Test
    void testReaderCasesAllBrushesRoom() {
        JsonReader jsonReader = new JsonReader("./data/testReaderAllBrushesRoom.json");
        try {
            BrushesRoom br = jsonReader.readBrushesRoom();
            Brush b1 = br.getCases().get(0).getBrushes().get(0);
            Brush b2 = br.getCases().get(1).getBrushes().get(0);
            assertEquals(2, br.getCases().size());
            assertEquals("realistic", br.getCases().get(0).getName());
            assertFalse(br.getCases().get(0).getBrushes().isEmpty());
            checkBrushValues(b1, 50, "tree", 100, 100, 0, 1);
            assertEquals("stamps", br.getCases().get(1).getName());
            assertFalse(br.getCases().get(1).getBrushes().isEmpty());
            checkBrushValues(b2, 50, "face", 50, 50, 50, 1);
        } catch (IOException e) {
            fail("Didn't expect an exception");
        }
    }
}
