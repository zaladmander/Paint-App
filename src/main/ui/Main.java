package ui;

import model.EventLogHelper;
import ui.gui.MainMenu;

public class Main {
    public static void main(String[] args) {
        //new PaintApp();
        new MainMenu();
        EventLogHelper.printLogs();
    }
}
