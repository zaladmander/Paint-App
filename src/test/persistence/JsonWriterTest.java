package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.InvalidPathException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{

    @Test
    void testSourceNotFound() {
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
    void testWriterDrawingRoomEmpty() {
        DrawingRoom dr = new DrawingRoom();
        JsonWriter writer = new JsonWriter("./data/testWriterDrawingRoomEmpty.json");
        JsonReader reader = new JsonReader("./data/testWriterDrawingRoomEmpty.json");
        try {
            writer.open();
            writer.writeDrawingRoom(dr);
            writer.close();
            dr = reader.readDrawingRoom();
            assertTrue(dr.getCanvases().isEmpty());
        } catch (IOException | InvalidPathException e) {
            fail("Exception not expected");
        }
    }

    @Test
    void testWriterDrawingRoomCanvasesPresent() {
        DrawingRoom dr = new DrawingRoom();
        Canvas c1 = new Canvas("blank", 800, 800);
        Canvas c2 = new Canvas("photo", 850, 1800);
        dr.addCanvas(c1);
        dr.addCanvas(c2);
        JsonWriter writer = new JsonWriter("./data/testWriterDrawingRoomCanvasesPresent.json");
        JsonReader reader = new JsonReader("./data/testWriterDrawingRoomCanvasesPresent.json");
        try {
            writer.open();
            writer.writeDrawingRoom(dr);
            writer.close();
            dr = reader.readDrawingRoom();
            assertFalse(dr.getCanvases().isEmpty());
            Canvas jsonCanvas1 = dr.getCanvases().get(0);
            Canvas jsonCanvas2 = dr.getCanvases().get(1);
            assertEquals("blank", jsonCanvas1.getType());
            assertEquals(800, jsonCanvas1.getWidth());
            assertEquals(800, jsonCanvas1.getHeight());
            assertEquals("photo", jsonCanvas2.getType());
            assertEquals(1800, jsonCanvas2.getWidth());
            assertEquals(850, jsonCanvas2.getHeight());
        } catch (IOException | InvalidPathException e) {
            fail("Exception not expected");
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
        } catch (IOException | InvalidPathException e) {
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
        } catch (IOException | InvalidPathException e) {
            fail("Exception not expected");
        }
    }

    @Test
    void testWriterAllBrushesRoom() {
        BrushesRoom br = new BrushesRoom();
        PencilCase pc1 = new PencilCase("realistic");
        PencilCase pc2 = new PencilCase("stamps");
        pc1.addBrush(new Brush(50, "tree", 100, 100, 0, 1));
        pc2.addBrush(new Brush(50, "face", 50, 50, 50, 1));
        br.addPencilCase(pc1);
        br.addPencilCase(pc2);
        JsonWriter writer = new JsonWriter("./data/testWriterAllBrushesRoom.json");
        JsonReader reader = new JsonReader("./data/testWriterAllBrushesRoom.json");
        try {
            writer.open();
            writer.writeBrushRoom(br);
            writer.close();
            br = reader.readBrushesRoom();
            assertFalse(br.getCases().isEmpty());
            PencilCase c1 = br.getCases().get(0);
            PencilCase c2 = br.getCases().get(1);
            assertEquals("realistic", c1.getName());
            assertFalse(c1.getBrushes().isEmpty());
            Brush b1 = c1.getBrush(0);
            checkBrushValues(b1, 50, "tree", 100, 100, 0, 1);
            assertEquals("stamps", c2.getName());
            assertFalse(c2.getBrushes().isEmpty());
            Brush b2 = c2.getBrush(0);
            checkBrushValues(b2, 50, "face", 50, 50, 50, 1);
        } catch (IOException | InvalidPathException e) {
            fail("Exception not expected");
        }
    }
}
