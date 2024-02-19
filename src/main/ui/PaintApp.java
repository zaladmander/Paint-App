package ui;

import model.Brush;
import model.Canvas;
import model.Case;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// represents a paint application with ui, different menus
// main menu, cases menu, brushes menu, edit menu, canvas menu
// takes user input to travel from menu to menu
public class PaintApp {
    private Scanner input;
    private List<Case> cases;
    private List<Canvas> canvases;

    // EFFECTS: constructs a PaintApp
    public PaintApp() {
        runPaintApp();
    }

    // EFFECTS: runs a console based ui for PaintApp
    private void runPaintApp() {
        initialize();

        runMainMenu();
        System.out.println("Quitting...");
    }

    // EFFECTS: runs (displays and processes) main menu
    private void runMainMenu() {
        boolean run = true;
        String command;
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

    private void runCasesMenu() {
        boolean run = true;
        String command;
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

    private void runBrushesMenu(String name) {
        boolean run = true;
        String command;
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

    private void runDrawMenu() {
        boolean run = true;
        String command;
        while (run) {
            displayDrawMenu();
            command = input.next();

            if (command.equals("q")) {
                run = false;
            } else {
                processDrawCommand(command);
            }
        }
    }

    private void processDrawCommand(String command) {
        if (command.equals("1")) {
            editCanvas();
        } else if (command.equals("2")) {
            System.out.println("Type canvas position on list to DELETE");
            int index = stringToInt(input.next());
            deleteCanvas(index);
        } else {
            System.out.println("invalid input...");
        }
    }

    private void processMainCommand(String command) {
        if (command.equals("1")) {
            runDrawMenu();
        } else if (command.equals("2")) {
            runCasesMenu();
        } else {
            System.out.println("invalid input...");
        }
    }

    private void processCasesCommand(String command) {
        if (command.equals("1")) {
            System.out.println("Name your case");
            String name = input.next();
            cases.add(new Case(name));
        } else if (command.equals("2")) {
            System.out.println("Type case to DELETE");
            String caseToDelete = input.next();
            deleteCase(caseToDelete);
        } else if (isNameInListCases(command)) {
            runBrushesMenu(command);
        } else {
            System.out.println("invalid input...");
        }
    }

    private void processBrushesCommand(String command, Case pencilCase) {
        if (command.equals("1")) {
            System.out.println("Type name:");
            String name = input.next();
            System.out.println("Type brush size:");
            int size = stringToInt(input.next());
            pencilCase.addBrush(new Brush(size, name));
        } else if (command.equals("2")) {
            System.out.println("Type brush to DELETE");
            String brushToDelete = input.next();
            deleteBrush(brushToDelete, pencilCase);
        } else if (isNameInListBrushes(command, pencilCase)) {
            displayEditMenu(command, pencilCase);
            String edit = input.next();
            processEditCommand(edit, pencilCase.getBrushWithName(command));
        } else {
            System.out.println("invalid input...");
        }
    }

    private void processEditCommand(String command, Brush brush) {
        switch (command) {
            case "1":
                editColor(brush);
                break;
            case "2":
                System.out.println("Type opacity value [0 - 1]");
                brush.setOpacity(stringToInt(input.next()));
                break;
            case "3":
                System.out.println("Type name:");
                brush.setName(input.next());
                break;
            case "4":
                System.out.println("Type size value (>=1)");
                brush.setSize(stringToInt(input.next()));
                break;
        }
    }

    private void editColor(Brush brush) {
        System.out.println("Type red value [0 - 255]");
        brush.setRed(stringToInt(input.next()));
        System.out.println("Type green value [0 - 255]");
        brush.setGreen(stringToInt(input.next()));
        System.out.println("Type blue value [0 - 255]");
        brush.setBlue(stringToInt(input.next()));
    }

    public void editCanvas() {
        System.out.println("Canvas type (blank or photo)");
        String type = input.next();
        System.out.println("Width");
        int width = stringToInt(input.next());
        System.out.println("Height:");
        int height = stringToInt(input.next());
        canvases.add(new Canvas(type, height, width));
    }

    private void displayDrawMenu() {
        System.out.println("CANVAS MENU");
        if (canvases.isEmpty()) {
            System.out.println("No canvases...");
        } else {
            for (Canvas c : canvases) {
                System.out.println(c.getWidth() + "x" + c.getHeight());
            }
        }
        System.out.println("\t(1) Make a canvas");
        System.out.println("\t(2) Delete a canvas");
        System.out.println("\tPress 'q' to go back");
    }

    private void displayMainMenu() {
        System.out.println("MAIN MENU");
        System.out.println("\t(1) Draw on a new canvas");
        System.out.println("\t(2) Look at brushes");
        System.out.println("\tPress 'q' to quit");
    }

    private void displayCasesMenu() {
        System.out.println("CASES MENU");
        System.out.println("Your cases:");
        if (cases.isEmpty()) {
            System.out.println("You have no cases.");
        } else {
            for (Case c : cases) {
                System.out.println(c.getName());
            }
        }
        System.out.println("\t(1) Make a case");
        System.out.println("\t(2) Delete a case");
        System.out.println("\tType case name (case sensitive) to open");
        System.out.println("\tPress 'q' to go back");
    }

    private void displayBrushesMenu(Case pencilCase) {
        System.out.println("BRUSHES MENU");
        System.out.println("Your brushes:");
        if (pencilCase.getBrushes().isEmpty()) {
            System.out.println("No brushes :(");
        } else {
            for (Brush b : pencilCase.getBrushes()) {
                System.out.println(b.getName());
            }
        }
        System.out.println("\t(1) Make a brush");
        System.out.println("\t(2) Delete a brush");
        System.out.println("\tType brush name (case sensitive) to look or edit");
        System.out.println("\tor press 'q' to go back");
    }

    private void displayEditMenu(String name, Case pencilCase) {
        String red = String.valueOf(pencilCase.getBrushWithName(name).getRed());
        String green = String.valueOf(pencilCase.getBrushWithName(name).getGreen());
        String blue = String.valueOf(pencilCase.getBrushWithName(name).getBlue());
        System.out.println("EDIT MENU");
        System.out.println("\t(1) - Color = " + red + "-" + green + "-" + blue);
        System.out.println("\t(2) - Opacity = " + pencilCase.getBrushWithName(name).getOpacity());
        System.out.println("\t(3) - Name = " + name);
        System.out.println("\t(4) - Size = " + pencilCase.getBrushWithName(name).getSize());
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
        canvases = new ArrayList<>();
    }

    private int stringToInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            // default size
            return 10;
        }
    }

    private void deleteCase(String caseToDelete) {
        cases.remove(getCaseWithName(caseToDelete));
    }

    private void deleteBrush(String brushToDelete, Case pencilCase) {
        Brush brush = pencilCase.getBrushWithName(brushToDelete);
        pencilCase.getBrushes().remove(brush);
    }

    private void deleteCanvas(int index) {
        if (index > 0) {
            canvases.remove(index - 1);
        }
    }

}
