package ui;

import model.Brush;
import model.Case;

import java.util.Scanner;

// represents a paint application with ui
public class PaintApp {
    private Case pencilCase;
    private Scanner input;

    public PaintApp() {
        runPaintApp();
    }

    private void runPaintApp() {
        boolean run = true;
        String command = null;

        initialize();

        while (run) {
            displayMainMenu();
            command = input.next();

            if (command.equals("q")) {
                run = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Quitting...");
    }

    private void processCommand(String command) {
        if (command.equals("c")) {
            displayListOfBrushes();
        } else {
            System.out.println("invalid input...");
        }
    }

    private void displayMainMenu() {
        System.out.println("Press 'c' to look at your brushes");
        System.out.println("Press 'q' to quit");
    }

    private void displayListOfBrushes() {
        if (pencilCase.getBrushes().isEmpty()) {
            System.out.println("No brushes :(");
        } else {
            for (Brush b : pencilCase.getBrushes()) {
                System.out.println(b.getName());
            }
        }
    }

    private void displayBrush() {

        System.out.println("Press 'e' to edit your brush");
    }

    private void displayEditMenu() {

    }

    private void initialize() {
        pencilCase = new Case();
        input = new Scanner(System.in);
    }
}
