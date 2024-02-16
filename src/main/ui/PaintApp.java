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

    }

    private void displayMainMenu() {
        System.out.println("Press 'c' to look at your brushes");
        System.out.println("Press 'q' to quit");
    }

    private void displayListOfBrushes() {
        for (Brush b : pencilCase.getBrushes()) {
            System.out.println();
        }
    }

    private void displayBrush() {

        System.out.println("Press 'e' to edit your brush");
    }

    private void displayEditMenu() {

    }

    private void initialize() {

    }
}
