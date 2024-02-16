package model;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BrushTest {
    Brush b1;
    Brush b2;

    @BeforeEach
    void setup() {
        b1 = new Brush(10);
        b2 = new Brush(50);
    }

    @Test
    void testSetOpacityToOkayNumber() {
        b1.setOpacity(0);
        assertEquals(0, b1.getOpacity());
    }

    @Test
    void testSetRedOverMax() {
        b1.setRed(256);
        assertEquals(0, b1.getRed());
    }

    @Test
    void testSetRedInBetweenRange() {
        b1.setRed(255);
        assertEquals(255, b1.getRed());
    }

    @Test
    void testSetRedUnderMin() {
        b1.setRed(-1);
        assertEquals(0, b1.getRed());
    }
}