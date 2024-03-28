package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents an abstract GUI window
public abstract class WindowGUI extends JFrame implements ActionListener {
    protected static final Color BG_COLOR = Color.white;
    protected static final String windowLabel = "Paint!";

    protected JMenuBar menuBar;

    private JButton button;
    private JMenuItem saveMenuItem;
    private JMenuItem loadMenuItem;
    private JMenuItem quitMenuItem;


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

    // EFFECTS: adds a menu bar to the given frame
    protected void initializeMenuBar(JFrame frame) {
        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        addMenuItemsFile(fileMenu);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
    }

    // EFFECTS: adds file menu dropdowns to given JMenu
    protected void addMenuItemsFile(JMenu menu) {
        saveMenuItem = new JMenuItem("Save");
        loadMenuItem = new JMenuItem("Load");
        quitMenuItem = new JMenuItem("Quit");


        saveMenuItem.addActionListener(this);
        loadMenuItem.addActionListener(this);
        quitMenuItem.addActionListener(this);


        menu.add(saveMenuItem);
        menu.add(loadMenuItem);
        menu.add(quitMenuItem);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveMenuItem) {
            JOptionPane.showMessageDialog(null,
                    "Drawing has been (un)successfully saved!",
                    windowLabel, JOptionPane.PLAIN_MESSAGE);
        } else if (e.getSource() == loadMenuItem) {
            JOptionPane.showMessageDialog(null,
                    "Drawing has been (un)successfully loaded!",
                    windowLabel, JOptionPane.PLAIN_MESSAGE);
        } else if (e.getSource() == quitMenuItem) {
            int response = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to quit?",
                    windowLabel, JOptionPane.OK_CANCEL_OPTION);
            if (response == 0) {
                this.dispose();
            }
        }
    }
}
