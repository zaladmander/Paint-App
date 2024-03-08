package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CanvasTest {
    Canvas c1;
    Canvas c2;

    @BeforeEach
    void setup() {
        c1 = new Canvas("blank", 800, 800);
        c2 = new Canvas("photo", 1080, 800);
    }

    @Test
    void testSetType() {
        c1.setType("photo");
        assertEquals("photo", c1.getType());
    }

    @Test
    void testSetTypeInvalidType() {
        c1.setType("goobygooby");
        assertEquals("blank", c1.getType());
    }

    @Test
    void testSetWidth() {
        c1.setWidth(1000);
        assertEquals(1000, c1.getWidth());
    }

    @Test
    void testSetHeight() {
        c1.setHeight(1000);
        assertEquals(1000, c1.getHeight());
    }

    @Test
    void testToJson() {
        JSONObject jsc1 = c1.toJson();
        assertEquals("blank", jsc1.getString("type"));
        assertEquals(800, jsc1.getInt("height"));
        assertEquals(800, jsc1.getInt("width"));

        JSONObject jsc2 = c2.toJson();
        assertEquals("photo", jsc2.getString("type"));
        assertEquals(1080, jsc2.getInt("height"));
        assertEquals(800, jsc2.getInt("width"));
    }
}
