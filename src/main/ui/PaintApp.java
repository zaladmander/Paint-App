package ui;

import model.Brush;
import model.Case;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// represents a paint application with ui
public class PaintApp {
    private Scanner input;
    private List<Case> cases;

    public PaintApp() {
        runPaintApp();
    }

    private void runPaintApp() {
        boolean run = true;
        initialize();

        runMainMenu(run);
        System.out.println("Quitting...");
    }

    private void runMainMenu(boolean run) {
        String command = null;
        while (run) {
            displayMainMenu();
            command = input.next();

            if (command.equals("q")) {
                run = false;
            } else {
                processMainCommand(command);
            }
        }
    }

    private void runCasesMenu(boolean run) {
        String command = null;
        while (run) {
            displayCasesMenu();
            command = input.next();

            if (command.equals("q")) {
                run = false;
            } else {
                processCasesCommand(command);
            }
        }
    }

    private void runBrushesMenu(boolean run, String name) {
        String command = null;
        Case pencilCase = getCaseWithName(name);
        while (run) {
            displayBrushesMenu(pencilCase);
            command = input.next();

            if (command.equals("q")) {
                run = false;
            } else {
                processBrushesCommand(command, pencilCase);
            }
        }
    }

    private void processMainCommand(String command) {
        if (command.equals("d")) {
            draw();
        } else if (command.equals("c")) {
            runCasesMenu(true);
        } else {
            System.out.println("invalid input...");
        }
    }

    private void processCasesCommand(String command) {
        if (command.equals("m")) {
            System.out.println("Name your case");
            String name = input.next();
            cases.add(new Case(name));
        } else if (isNameInListCases(command)) {
            runBrushesMenu(true, command);
        } else {
            System.out.println("invalid input...");
        }
    }

    private void processBrushesCommand(String command, Case pencilCase) {
        if (command.equals("m")) {
            System.out.println("Type name:");
            String name = input.next();
            System.out.println("Type brush size:");
            int size = stringToInt(input.next());
            pencilCase.addBrush(new Brush(size, name));
        } else if (isNameInListBrushes(command, pencilCase)) {
            displayEditMenu();
            String edit = input.next();
            processEditCommand(edit, pencilCase.getBrushWithName(command));
            //TODO: change to editing
        } else {
            System.out.println("invalid input...");
        }
    }

    private void processEditCommand(String command, Brush brush) {
        if (command.equals("1")) {
            editColor(command, brush);
        } else if (command.equals("2")) {
            System.out.println("Type opacity value [0 - 1]");
            brush.setOpacity(stringToInt(input.next()));
        } else if (command.equals("3")) {
            System.out.println("Type name:");
            brush.setName(input.next());
        } else if (command.equals("4")) {
            System.out.println("Type size value (>=1)");
            brush.setSize(stringToInt(input.next()));
        }
    }

    private void editColor(String command, Brush brush) {
        System.out.println("Type red value [0 - 255]");
        brush.setRed(stringToInt(input.next()));
        System.out.println("Type green value [0 - 255]");
        brush.setGreen(stringToInt(input.next()));
        System.out.println("Type blue value [0 - 255]");
        brush.setBlue(stringToInt(input.next()));
    }

    private void draw() {
        System.out.println("This functionality does not work yet!");
    }

    private void displayMainMenu() {
        System.out.println("MAIN MENU");
        System.out.println("\tPress 'd' to draw on a new canvas");
        System.out.println("\tPress 'c' to look at brushes");
        System.out.println("\tPress 'q' to quit");
    }

    private void displayCasesMenu() {
        System.out.println("Your cases:");
        if (cases.isEmpty()) {
            System.out.println("You have no cases.");
        } else {
            for (Case c : cases) {
                System.out.println(c.getName());
            }
        }
        System.out.println("\tPress 'm' to make a case");
        System.out.println("\tType case name (case sensitive) to open");
        System.out.println("\tPress 'q' to go back");
    }

    private void displayBrushesMenu(Case pencilCase) {
        System.out.println("Your brushes:");
        if (pencilCase.getBrushes().isEmpty()) {
            System.out.println("No brushes :(");
        } else {
            for (Brush b : pencilCase.getBrushes()) {
                System.out.println(b.getName());
            }
        }
        System.out.println("\tPress 'm' to make a brush");
        System.out.println("\tType brush name (case sensitive) to look or edit");
        System.out.println("\tor press 'q' to go back");
    }

    private void displayEditMenu() {
        System.out.println("(1) - Color");
        System.out.println("(2) - Opacity");
        System.out.println("(3) - Name");
        System.out.println("(4) - Size");
        System.out.println("or press 'q' to go back");
    }

    private boolean isNameInListCases(String name) {
        for (Case c : cases) {
            if (c.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private boolean isNameInListBrushes(String name, Case pencilCase) {
        for (Brush b : pencilCase.getBrushes()) {
            if (b.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private Case getCaseWithName(String name) {
        for (Case c : cases) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    private void initialize() {
        input = new Scanner(System.in);
        cases = new ArrayList<>();
    }

    private int stringToInt(String string) {
        try {
            int output = Integer.parseInt(string);
            return output;
        } catch (NumberFormatException e) {
            // default size
            return 10;
        }
    }
}
