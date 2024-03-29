package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DrawingRoomTest {
    DrawingRoom dr1;
    Canvas c1;

    @BeforeEach
    void setup() {
        dr1 = DrawingRoom.getDrawingRoom();
        dr1.reset();
        c1 = new Canvas("photo", 800, 800);
        dr1.addCanvas(c1);
    }

    @Test
    void testDeleteCanvas() {
        assertEquals(c1, dr1.getCanvases().get(0));
        dr1.deleteCanvas(1);
        assertTrue(dr1.getCanvases().isEmpty());
    }

    @Test
    void testDeleteCanvasPositionInvalid() {
        assertEquals(c1, dr1.getCanvases().get(0));
        dr1.deleteCanvas(0);
        assertFalse(dr1.getCanvases().isEmpty());
        dr1.deleteCanvas(-1);
        assertFalse(dr1.getCanvases().isEmpty());
    }

    @Test
    void testToJson() {
        JSONObject jsdr1 = dr1.toJson();
        JSONObject jsc1 = jsdr1.getJSONArray("canvases").getJSONObject(0);
        assertEquals("photo", jsc1.getString("type"));
        assertEquals(800, jsc1.getInt("height"));
        assertEquals(800, jsc1.getInt("width"));

    }
}
