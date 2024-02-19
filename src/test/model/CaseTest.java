package model;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CaseTest {
    Case c1;
    Brush b1;
    Brush b2;

    @BeforeEach
    void setup() {
        c1 = new Case("my cool brushes");
        b1 = new Brush(25, "stinky");
        b2 = new Brush(50, "abstract");
    }

    @Test
    void testAddBrush() {
        c1.addBrush(b1);
        assertEquals(b1, c1.getBrush(0));
    }

    @Test
    void testRemoveBrush() {

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
}
