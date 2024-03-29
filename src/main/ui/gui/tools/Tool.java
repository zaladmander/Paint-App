package ui.gui.tools;

import ui.gui.PaintingEditorMenu;

import javax.swing.*;
import java.awt.event.MouseEvent;


// uses references from the SimpleDrawingPlayer-Starter from GitHub
public abstract class Tool {
    protected JButton button;
    protected PaintingEditorMenu editor;
    private boolean active;

    public Tool(PaintingEditorMenu editor, JComponent parent) {
        this.editor = editor;
        createButton(parent);
        addToParent(parent);
        active = false;
        addListener();
    }

    protected abstract void addListener();

    private void addToParent(JComponent parent) {
        parent.add(button);
    }

    // EFFECTS: creates button to activate tool
    protected abstract void createButton(JComponent parent);

    // EFFECTS: default behaviour does nothing
    public void mousePressed(MouseEvent e) {

    }

    // EFFECTS: default behaviour does nothing
    public void mouseReleased(MouseEvent e) {

    }

    // EFFECTS: default behaviour does nothing
    public void mouseClicked(MouseEvent e) {

    }

    // EFFECTS: default behaviour does nothing
    public void mouseDragged(MouseEvent e) {

    }
}
