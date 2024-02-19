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
        } else if (command.equals("o")) {
            System.out.println(cases);
        } else {
            System.out.println("invalid input...");
        }
    }

    private void draw() {
        System.out.println("This functionality does not work yet!");
    }

    private void displayMainMenu() {
        System.out.println("Press 'd' to draw on a new canvas");
        System.out.println("Press 'c' to look at brushes");
        System.out.println("Press 'q' to quit");
    }

    private void displayCasesMenu() {
        String command = null;
        System.out.println("Your cases:");
        if (cases.isEmpty()) {
            System.out.println("You have no cases.");
        } else {
            for (Case c : cases) {
                System.out.println(c.getName());
            }
        }
        System.out.println("Press 'm' to make a case");
        System.out.println("Press 'o' to look into a case");
        System.out.println("Press 'q' to go back");
    }

    private void displayListOfBrushes(Case pencilCase) {
        if (pencilCase.getBrushes().isEmpty()) {
            System.out.println("No brushes :(");
        } else {
            for (Brush b : pencilCase.getBrushes()) {
                System.out.println(b.getName());
            }
        }
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

    private void initialize() {
        input = new Scanner(System.in);
        cases = new ArrayList<>();
    }
}
