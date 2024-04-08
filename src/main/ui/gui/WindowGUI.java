package ui.gui;

import model.EventLogHelper;
import ui.FileHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// represents an abstract GUI window
// TODO: hierarchy may be a bit scuffed so try to clean it up
public abstract class WindowGUI extends JFrame implements ActionListener, WindowListener {
    protected static final Color BG_COLOR = Color.white;
    protected static final Color BG_COLOR2 = new Color(200, 200, 160);
    protected static final String windowLabel = "Paint!";
    protected static final int WIDTH = 400;
    protected static final int HEIGHT = 450;

    protected JMenuBar menuBar;

    protected JMenuItem saveMenuItem;
    protected JMenuItem loadMenuItem;
    protected JMenuItem resetMenuItem;
    protected JMenuItem quitMenuItem;
    protected JMenuItem paintMainMenuItem;
    protected JMenuItem brushesMenuItem;
    protected JMenu paintMenu;
    protected JMenu fileMenu;
    protected JMenu toolsMenu;

    // EFFECTS: constructs a JFrame with the title windowLabel
    public WindowGUI(String windowLabel) {
        super(windowLabel);
    }

    // MODIFIES: this
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

    // MODIFIES: menuBar, paintMenu, fileMenu, toolsMenu
    // EFFECTS: adds a menu bar to the given frame
    protected void initializeMenuBar(JFrame frame) {
        menuBar = new JMenuBar();
        paintMenu = new JMenu("Paint!");
        fileMenu = new JMenu("File");
        toolsMenu = new JMenu("Tools");
        addMenuItemsPaint(paintMenu);
        addMenuItemsFile(fileMenu);
        addMenuItemsTools(toolsMenu);
        menuBar.add(paintMenu);
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        frame.setJMenuBar(menuBar);
    }

    // MODIFIES: this, menu, brushesMenuItem
    // EFFECTS: adds tools menu dropdowns to given JMenu
    private void addMenuItemsTools(JMenu menu) {
        brushesMenuItem = new JMenuItem("Brushes");
        brushesMenuItem.addActionListener(this);
        menu.add(brushesMenuItem);
    }

    // MODIFIES: this, menu, paintMainMenuItem, quitMenuItem
    // EFFECTS: adds "paint" menu dropdowns to given JMenu
    private void addMenuItemsPaint(JMenu menu) {
        paintMainMenuItem = new JMenuItem("Main Menu");
        quitMenuItem = new JMenuItem("Quit");
        paintMainMenuItem.addActionListener(this);
        quitMenuItem.addActionListener(this);
        menu.add(paintMainMenuItem);
        menu.add(quitMenuItem);
    }

    // MODIFIES: this, menu, saveMenuItem, loadMenuItem, resetMenuItem
    // EFFECTS: adds file menu dropdowns to given JMenu
    protected void addMenuItemsFile(JMenu menu) {
        saveMenuItem = new JMenuItem("Save");
        loadMenuItem = new JMenuItem("Load");
        resetMenuItem = new JMenuItem("Reset");
        saveMenuItem.addActionListener(this);
        loadMenuItem.addActionListener(this);
        resetMenuItem.addActionListener(this);
        menu.add(saveMenuItem);
        menu.add(loadMenuItem);
        menu.add(resetMenuItem);
    }

    // popup windows

    // EFFECTS: shows abstract popup message window for a menu item
    protected void textMenuItemDialog(String text) {
        JOptionPane.showMessageDialog(null, text, windowLabel, JOptionPane.PLAIN_MESSAGE);
    }

    // EFFECTS: an abstract popup dialog response window that has
    //          buttons for OK or Cancel, returns an int response depending
    //          on what buttons are clicked in popup window
    // TODO: this disposes the JFrame so maybe make another method like this or take boolean input
    private int confirmMenuItemDialog(String text, Boolean dispose) {
        int response = JOptionPane.showConfirmDialog(null, text,
                windowLabel, JOptionPane.OK_CANCEL_OPTION);
        if (response == 0 && dispose) {
            this.dispose();
        }
        return response;
    }

    // MODIFIES: BrushesRoom, DrawingRoom
    // EFFECTS: asks the user if they want to save, if ok is clicked, then save
    private void saveOptionMenuDialog() {
        if (confirmMenuItemDialog("Are you sure you want to save?", false) == 0) {
            FileHelper.getFileHelper().saveAll();
            textMenuItemDialog("All data has been successfully saved!");
        }
    }

    // EFFECTS: asks the user if they want to load, if ok is clicked, then load
    private void loadOptionMenuDialog() {
        if (confirmMenuItemDialog("Are you sure you want to load?", false) == 0) {
            FileHelper.getFileHelper().loadAll();
            textMenuItemDialog("All data has been successfully loaded!");
        }
    }

    // EFFECTS: asks the user if they want to quit, if ok is clicked, then quit, also prompts to save
    private void quitOptionMenuDialog() {
        if (confirmMenuItemDialog("Are you sure you want to quit?", true) == 0) {
            saveOptionMenuDialog();
            EventLogHelper.printLogs();
            System.exit(0);
        }
    }

    // EFFECTS: asks the user if they want to go to main menu, if ok is clicked, then create main menu,
    //          also prompts to save
    private void mainMenuOptionMenuDialog() {
        if (confirmMenuItemDialog("Are you sure you want to go to the Main Menu?", true) == 0) {
            saveOptionMenuDialog();
            new MainMenu();
        }
    }

    // EFFECTS: asks the user if they want to reset all data, if ok is clicked, then create invoke reset()
    private void resetOptionMenuDialog() {
        if (confirmMenuItemDialog("Are you sure you want to go reset all data? This cannot be undone!",
                true) == 0) {
            FileHelper.getFileHelper().reset();
            new MainMenu();
            textMenuItemDialog("All data has been successfully reset.");
        }
    }

    // EFFECTS: listens for ActionEvent, and displays message depending on input
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveMenuItem) {
            saveOptionMenuDialog();
        } else if (e.getSource() == loadMenuItem) {
            loadOptionMenuDialog();
        } else if (e.getSource() == quitMenuItem) {
            quitOptionMenuDialog();
        } else if (e.getSource() == paintMainMenuItem) {
            mainMenuOptionMenuDialog();
        } else if (e.getSource() == brushesMenuItem) {
            new PencilCasesMenu(this);
        } else if (e.getSource() == resetMenuItem) {
            resetOptionMenuDialog();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        saveOptionMenuDialog();
        EventLogHelper.printLogs();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
