package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.*;

public class BrushesRoomTest {
    BrushesRoom br1;
    PencilCase pc1;
    Brush b1;

    @BeforeEach
    void setup() {
        b1 = new Brush(10, "test", 0, 0, 0, 1);
        br1 = new BrushesRoom();
        pc1 = new PencilCase("testable");
        pc1.addBrush(b1);
        br1.addPencilCase(pc1);
    }

    @Test
    void testIsNameInListBrushes() {
        assertFalse(br1.isNameInListBrushes("JKHASKJSHAJKHAS", pc1));
        assertTrue(br1.isNameInListBrushes("test", pc1));
    }

    @Test
    void testIsNameInListCases() {
        assertTrue(br1.isNameInListCases("testable"));
        assertFalse(br1.isNameInListCases("dorito flavoured chips"));
    }

    @Test
    void testGetCaseWithName() {
        assertEquals(pc1, br1.getCaseWithName("testable"));
        assertNull(br1.getCaseWithName("nonexistant"));
    }

    @Test
    void testDeleteBrush() {
        assertEquals(b1, pc1.getBrush(0));
        br1.deleteBrush("test", pc1);
    }

    @Test
    void testDeleteCase() {
        assertEquals(pc1, br1.getCaseWithName("testable"));
        br1.deleteCase("testable");
        assertFalse(br1.isNameInListCases("testable"));
    }

    @Test
    void testGetCases() {
        assertFalse(br1.getCases().isEmpty());
        assertEquals(pc1, br1.getCaseWithName("testable"));
    }

    @Test
    void testToJson() {
        JSONObject jsbr1 = br1.toJson();
        JSONObject jsc1 = jsbr1.getJSONArray("cases").getJSONObject(0);
        assertEquals("testable", jsc1.getString("name"));

        JSONObject jsb1 = jsc1.getJSONArray("brushes").getJSONObject(0);

        assertEquals("test", jsb1.getString("name"));
        assertEquals(0, jsb1.getInt("red"));
        assertEquals(0, jsb1.getInt("green"));
        assertEquals(0, jsb1.getInt("blue"));
        assertEquals(1, jsb1.getDouble("opacity"));
        assertEquals(10, jsb1.getInt("size"));
    }
}
