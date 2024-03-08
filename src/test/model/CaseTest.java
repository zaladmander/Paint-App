package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CaseTest {
    PencilCase c1;
    Brush b1;
    Brush b2;

    @BeforeEach
    void setup() {
        c1 = new PencilCase("my cool brushes");
        b1 = new Brush(25, "stinky",0,0,0,1);
        b2 = new Brush(50, "abstract",0,0,0,1);
    }

    @Test
    void testAddBrush() {
        c1.addBrush(b1);
        assertEquals(b1, c1.getBrush(0));
    }

    @Test
    void testRemoveBrush() {
        c1.addBrush(b2);
        assertEquals(1, c1.getBrushes().size());
        c1.removeBrush(0);
        assertEquals(0, c1.getBrushes().size());
    }

    @Test
    void testGetBrushWithName() {
        c1.addBrush(b2);
        assertEquals(b2, c1.getBrushWithName("abstract"));
    }

    @Test
    void testGetBrushWithNameMultiple() {
        c1.addBrush(b2);
        c1.addBrush(b1);
        assertEquals(b1, c1.getBrushWithName("stinky"));
    }

    @Test
    void testGetBrushWithNameNull() {
        assertNull(c1.getBrushWithName("abstract"));
    }

    @Test
    void testGetNumBrushesNone() {
        assertEquals(0, c1.getNumBrushes());
    }

    @Test
    void testGetNumBrushesOne() {
        c1.addBrush(b1);
        assertEquals(1, c1.getNumBrushes());
    }

    @Test
    void testGetNumBrushesMultiple() {
        c1.addBrush(b1);
        c1.addBrush(b2);
        assertEquals(2, c1.getNumBrushes());
    }

    @Test
    void testSetName() {
        c1.setName("cooooool");
        assertEquals("cooooool", c1.getName());
    }

    @Test
    void testToJson() {
        c1.addBrush(b1);
        JSONObject jsc1 = c1.toJson();
        assertEquals("my cool brushes", jsc1.getString("name"));
        JSONObject jsb1 = jsc1.getJSONArray("brushes").getJSONObject(0);
        assertEquals("stinky", jsb1.getString("name"));
        assertEquals(0, jsb1.getInt("red"));
        assertEquals(0, jsb1.getInt("green"));
        assertEquals(0, jsb1.getInt("blue"));
        assertEquals(1, jsb1.getDouble("opacity"));
        assertEquals(25, jsb1.getInt("size"));
    }
}
