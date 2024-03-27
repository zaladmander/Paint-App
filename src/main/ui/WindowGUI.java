package ui;

import javax.swing.*;
import java.awt.*;

// represents an abstract GUI window
public abstract class WindowGUI extends JFrame {
    protected static final Color BG_COLOR = Color.white;
    protected static final String windowLabel = "Paint!";

    public WindowGUI(String windowLabel) {
        super(windowLabel);
    }

    // EFFECTS: abstract function for initializing a window, sets
    //          layout of window by given layout, and window background
    //          color by given color
    protected void initializeWindow(LayoutManager layout, Color color, int width, int height) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setMinimumSize(new Dimension(width, height));
        getContentPane().setBackground(color);
        setLocationRelativeTo(null);
        setLayout(layout);
    }


}
