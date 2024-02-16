package model;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CaseTest {
    Case c1;
    Brush b1;

    @BeforeEach
    void setup() {
        c1 = new Case();
        b1 = new Brush(25);
    }

    @Test
    void testAddBrush() {
        c1.addBrush(b1);
        assertEquals(b1, c1.getBrush(0));
    }
}
