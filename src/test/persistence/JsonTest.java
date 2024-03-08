package persistence;

import model.Brush;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    public void checkBrushValues(Brush brush, int size,
                                 String name, int red, int green,
                                 int blue, double opacity) {
        assertEquals(name, brush.getName());
        assertEquals(size, brush.getSize());
        assertEquals(red, brush.getRed());
        assertEquals(green, brush.getGreen());
        assertEquals(blue, brush.getBlue());
        assertEquals(opacity, brush.getOpacity());
    }
}
