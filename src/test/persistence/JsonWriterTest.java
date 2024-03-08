package persistence;

import model.BrushesRoom;
import model.PencilCase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.InvalidPathException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    void testSourceNotFound() {
        BrushesRoom br = new BrushesRoom();
        JsonWriter writer = new JsonWriter("./data/heehoo/testARRRRGGGGG.json");
        try {
            writer.open();
            fail("No exceptions thrown :( ");
        } catch (IOException e) {
            // good stuff
        } catch (InvalidPathException e) {
            // ok
        }
    }

    @Test
    void testWriterEmptyBrushesRoom() {
        BrushesRoom br = new BrushesRoom();
        JsonWriter writer = new JsonWriter("./data/testWriterEmptyBrushesRoom.json");
        JsonReader reader = new JsonReader("./data/testWriterEmptyBrushesRoom.json");
        try {
            writer.open();
            writer.writeBrushRoom(br);
            writer.close();
            br = reader.readBrushesRoom();
            assertTrue(br.getCases().isEmpty());
        } catch (IOException e) {
            fail("Exception not expected");
        } catch (InvalidPathException e) {
            fail("Exception not expected");
        }
    }

    @Test
    void testWriterCasesNoBrushes() {
        BrushesRoom br = new BrushesRoom();
        br.addPencilCase(new PencilCase("sponges"));
        br.addPencilCase(new PencilCase("felts"));
        JsonWriter writer = new JsonWriter("./data/testWriterCasesNoBrushes.json");
        JsonReader reader = new JsonReader("./data/testWriterCasesNoBrushes.json");
        try {
            writer.open();
            writer.writeBrushRoom(br);
            writer.close();
            br = reader.readBrushesRoom();
            assertFalse(br.getCases().isEmpty());
            PencilCase c1 = br.getCases().get(0);
            PencilCase c2 = br.getCases().get(1);
            assertEquals("sponges", c1.getName());
            assertTrue(c1.getBrushes().isEmpty());
            assertEquals("felts", c2.getName());
            assertTrue(c2.getBrushes().isEmpty());
        } catch (IOException e) {
            fail("Exception not expected");
        } catch (InvalidPathException e) {
            fail("Exception not expected");
        }
    }
}
