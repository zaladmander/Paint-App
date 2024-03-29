package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DotTest {
    Dot d1;

    @BeforeEach
    void setup() {
        d1 = new Dot(Color.CYAN, 50, 25);
    }

    @Test
    void testGetColor() {
        assertEquals(Color.CYAN, d1.getColor());
    }

    @Test
    void testGetXCoord() {
        assertEquals(50, d1.getXcoord());
    }

    @Test
    void testGetYCoord() {
        assertEquals(25, d1.getYcoord());
    }
}
