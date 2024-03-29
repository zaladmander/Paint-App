package ui.gui;

import model.PencilCase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PencilCaseButton extends JButton implements ActionListener {

    PencilCaseButton(PencilCase pc, ActionListener parent) {
        super(pc.getName());
        this.addActionListener(parent);
        setBounds((WIDTH / 2) - 125, (HEIGHT / 2) - 50, 250, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            System.out.println("open case");
        }
    }
}
