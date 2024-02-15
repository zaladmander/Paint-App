package model;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BrushTest {
    Brush b1;
    Brush b2;

    @BeforeEach
    void setup() {
        b1 = new Brush(1, 10);
        b2 = new Brush(1, 50);
    }

    @Test
    void testSetOpacityToOkayNumber() {
        b1.setOpacity(0);
        assertEquals(0, b1.getOpacity());
    }
}