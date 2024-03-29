package ui.gui;

import ui.FileHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents an abstract GUI window
public abstract class WindowGUI extends JFrame implements ActionListener {
    protected static final Color BG_COLOR = Color.white;
    protected static final Color BG_COLOR2 = new Color(200, 200, 160);
    protected static final String windowLabel = "Paint!";
    protected static final int WIDTH = 400;
    protected static final int HEIGHT = 450;

    protected JMenuBar menuBar;

    private JMenuItem saveMenuItem;
    private JMenuItem loadMenuItem;
    private JMenuItem quitMenuItem;
    private JMenuItem paintMainMenuItem;
    private JMenuItem brushesMenuItem;

    // EFFECTS: constructs a JFrame with the title windowLabel
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
        setResizable(false);
        setVisible(true);
    }

    // Menu Bar

    // EFFECTS: adds a menu bar to the given frame
    protected void initializeMenuBar(JFrame frame) {
        menuBar = new JMenuBar();
        JMenu paintMenu = new JMenu("Paint!");
        JMenu fileMenu = new JMenu("File");
        JMenu toolsMenu = new JMenu("Tools");
        addMenuItemsPaint(paintMenu);
        addMenuItemsFile(fileMenu);
        addMenuItemsTools(toolsMenu);
        menuBar.add(paintMenu);
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        frame.setJMenuBar(menuBar);
    }

    // MODIFIES: menu
    // EFFECTS: adds tools menu dropdowns to given JMenu
    private void addMenuItemsTools(JMenu menu) {
        brushesMenuItem = new JMenuItem("Brushes");
        brushesMenuItem.addActionListener(this);
        menu.add(brushesMenuItem);
    }

    // MODIFIES: menu
    // EFFECTS: adds "paint" menu dropdowns to given JMenu
    private void addMenuItemsPaint(JMenu menu) {
        paintMainMenuItem = new JMenuItem("Main Menu");
        quitMenuItem = new JMenuItem("Quit");
        paintMainMenuItem.addActionListener(this);
        quitMenuItem.addActionListener(this);
        menu.add(paintMainMenuItem);
        menu.add(quitMenuItem);
    }

    // MODIFIES: menu
    // EFFECTS: adds file menu dropdowns to given JMenu
    protected void addMenuItemsFile(JMenu menu) {
        saveMenuItem = new JMenuItem("Save");
        loadMenuItem = new JMenuItem("Load");
        saveMenuItem.addActionListener(this);
        loadMenuItem.addActionListener(this);
        menu.add(saveMenuItem);
        menu.add(loadMenuItem);
    }

    // popup windows

    // EFFECTS: shows abstract popup message window for a menu item
    protected void textMenuItemDialog(String text) {
        JOptionPane.showMessageDialog(null, text, windowLabel, JOptionPane.PLAIN_MESSAGE);
    }

    // EFFECTS: an abstract popup dialog response window that has
    //          buttons for OK or Cancel, returns an int response depending
    //          on what buttons are clicked in popup window
    private int confirmMenuItemDialog(String text) {
        int response = JOptionPane.showConfirmDialog(null, text,
                windowLabel, JOptionPane.OK_CANCEL_OPTION);
        if (response == 0) {
            this.dispose();
        }
        return response;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveMenuItem) {
            FileHelper.getFileHelper().saveAll();
            textMenuItemDialog("Drawing has been (un)successfully saved!");
        } else if (e.getSource() == loadMenuItem) {
            //FileHelper.getFileHelper().loadAll();
            textMenuItemDialog("Drawing has been (un)successfully loaded!");
        } else if (e.getSource() == quitMenuItem) {
            confirmMenuItemDialog("Are you sure you want to quit?");
        } else if (e.getSource() == paintMainMenuItem) {
            if (confirmMenuItemDialog("Are you sure you want to go to the Main Menu?") == 0) {
                new MainMenu();
            }
        } else if (e.getSource() == brushesMenuItem) {
            new BrushesMenu();
        }
    }
}
