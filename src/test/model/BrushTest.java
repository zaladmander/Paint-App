package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrushTest {
    Brush b1;
    Brush b2;

    @BeforeEach
    void setup() {
        b1 = new Brush(10, "coolBrush");
        b2 = new Brush(50, "felt");
    }

    @Test
    void testSetOpacityToOkayNumber() {
        b1.setOpacity(0);
        assertEquals(0, b1.getOpacity());
    }

    @Test
    void testSetOpacityOverMax() {
        b1.setOpacity(1.5);
        assertEquals(1, b1.getOpacity());
    }

    @Test
    void testSetOpacityMaxBound() {
        b1.setOpacity(1);
        assertEquals(1, b1.getOpacity());
    }

    @Test
    void testSetOpacityUnderMin() {
        b1.setOpacity(-0.3);
        assertEquals(0, b1.getOpacity());
    }

    @Test
    void testSetOpacityMinBound() {
        b1.setOpacity(0);
        assertEquals(0, b1.getOpacity());
    }

    @Test
    void testSetOpacityDouble() {
        b1.setOpacity(0.35897);
        assertEquals(0.35897, b1.getOpacity());
    }

    @Test
    void testSetSizeLessThanBounds() {
        b1.setSize(0);
        assertEquals(1, b1.getSize());
    }

    @Test
    void testSetSize() {
        b1.setSize(256);
        assertEquals(256, b1.getSize());
    }
    
    @Test
    void testSetRedOverMax() {
        b1.setRed(256);
        assertEquals(255, b1.getRed());
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

    @Test
    void testSetGreenOverMax() {
        b1.setGreen(256);
        assertEquals(255, b1.getGreen());
    }

    @Test
    void testSetGreenInBetweenRange() {
        b1.setGreen(255);
        assertEquals(255, b1.getGreen());
    }

    @Test
    void testSetGreenUnderMin() {
        b1.setGreen(-1);
        assertEquals(0, b1.getGreen());
    }

    @Test
    void testSetBlueOverMax() {
        b1.setBlue(256);
        assertEquals(255, b1.getBlue());
    }

    @Test
    void testSetBlueInBetweenRange() {
        b1.setBlue(255);
        assertEquals(255, b1.getBlue());
    }

    @Test
    void testSetBlueUnderMin() {
        b1.setBlue(-1);
        assertEquals(0, b1.getBlue());
    }

    @Test
    void testSetName() {
        b1.setName("CoolerBrush");
        assertEquals("CoolerBrush", b1.getName());
    }

    @Test
    void testSetNameEmptyString() {
        b1.setName("");
        assertEquals("coolBrush", b1.getName());
    }
}