package ui.gui;

import model.DrawingRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents a menu that would pop up for users to give input as of what canvas to draw on
// TODO: make this class used (currently unused)
public class LoadCanvasMenu extends WindowGUI implements ActionListener {
    private DrawingRoom drawingRoom;

    LoadCanvasMenu(Component parent) {
        super("Load Canvas");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
