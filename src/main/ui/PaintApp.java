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
            displayEditMenu();
            String name = input.next();
            pencilCase.addBrush(new Brush(10, name));
        } else if (isNameInListBrushes(command, pencilCase)) {
            displayEditMenu();
            String name = input.next();
            //TODO: change to editing
            pencilCase.addBrush(new Brush(10, name));
        } else {
            System.out.println("invalid input...");
        }
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
        System.out.println("\tType brush name (case sensitive) to edit");
        System.out.println("\tor press 'q' to go back");
    }

    private void displayBrushMenu() {

        System.out.println("Press 'e' to edit your brush");
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
}
