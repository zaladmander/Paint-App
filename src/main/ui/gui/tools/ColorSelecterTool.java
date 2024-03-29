package ui.gui.tools;

import ui.gui.PaintingEditorMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class ColorSelecterTool extends Tool {
    private static final String label = "Color Selector";
    private Color previousColor;

    public ColorSelecterTool(PaintingEditorMenu editor, JComponent parent) {
        super(editor, parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new ColorSelectorClickHandler());
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(label);
        //button.setBounds(0, 0, 250, 250);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //JColorChooser.showDialog(null, "Color Selector", Color.black);
    }

    private class ColorSelectorClickHandler implements ActionListener {

        // MODIFIES: chosenColor, currentBrush
        // EFFECTS: opens up the color chooser when button is clicked,
        //          selected color changes current brush color
        @Override
        public void actionPerformed(ActionEvent e) {
            Color chosenColor = JColorChooser.showDialog(null, label, previousColor);
            if (chosenColor != null) {
                int red = chosenColor.getRed();
                int green = chosenColor.getGreen();
                int blue = chosenColor.getBlue();
                editor.getCurrentBrush().setRed(red);
                editor.getCurrentBrush().setGreen(green);
                editor.getCurrentBrush().setBlue(blue);

                previousColor = chosenColor;
            }
        }
    }
}
