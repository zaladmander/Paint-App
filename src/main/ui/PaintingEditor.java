package ui;

import ui.tools.ColorSelecterTool;

import javax.swing.*;
import java.awt.*;

public class PaintingEditor extends JFrame {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;

    PaintingEditor() {
        super("Paint!");
        initializeWindow();
    }

    private void initializeWindow() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH - 200, HEIGHT - 150));
        setSize(WIDTH, HEIGHT);
        getContentPane().setBackground(new Color(220, 220, 200));

        initializeTitleText(new JLabel());
        initializeToolBar();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeTitleText(JLabel titleText) {
        titleText.setText("Hello");
        titleText.setFont(new Font("Times New Roman", Font.PLAIN, 100));
        titleText.setVerticalAlignment(JLabel.CENTER);
        titleText.setHorizontalAlignment(JLabel.CENTER);

        titleText.setBackground(new Color(200,30,120));
        titleText.setOpaque(true);
        this.add(titleText);
    }

    private void initializeToolBar() {
        JPanel toolBar = new JPanel();
        toolBar.setLayout(new GridLayout(3,0));
        toolBar.setSize(new Dimension(0, 0));
        add(toolBar, BorderLayout.NORTH);

        //ColorSelecterTool rectTool = new ColorSelecterTool(this, toolBar);
    }
}
