package ui;

import javax.swing.*;
import java.awt.*;

public abstract class WindowGUI extends JFrame {
    private static final Color BG_COLOR = Color.white;


    public WindowGUI(String windowLabel) {
        super(windowLabel);
    }

    // EFFECTS: abstract function for initializing a window
    protected void initializeWindow(LayoutManager layout) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setLayout(layout);
    }


}
