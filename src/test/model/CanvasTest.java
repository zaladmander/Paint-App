package model;

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
}
