package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// represents a paint application with ui, different menus
// main menu, cases menu, brushes menu, edit menu, canvas menu
// takes user input to travel from menu to menu, lets the user customize
// brushes, pencilcases, and canvases
public class PaintApp {
    private static final String JSON_STORE_BR = "./data/brushesroom.json";
    private static final String JSON_STORE_DR = "./data/drawingroom.json";
    private static final String BACKTEXT = "\tPress 'q' to go back";
    private static final String INVALIDTEXT = "invalid input...";

    private Scanner input;
    private BrushesRoom brushroom;
    private DrawingRoom drawroom;
    private BrushesRoom emptyBrushRoom;
    private DrawingRoom emptyDrawingRoom;
    private JsonWriter jsonWriterBrushesRoom;
    private JsonReader jsonReaderBrushesRoom;
    private JsonWriter jsonWriterDrawingRoom;
    private JsonReader jsonReaderDrawingRoom;

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

    // EFFECTS: runs (displays and processes) case menu
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

    // EFFECTS: runs (displays and processes) brush menu
    private void runBrushesMenu(String name) {
        boolean run = true;
        String command;
        PencilCase pencilCase = brushroom.getCaseWithName(name);
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

    // EFFECTS: runs (displays and processes) draw/canvas menu
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

    // REQUIRES: index input is valid range in list
    // EFFECTS: processes commands from draw menu, 1 = make a canvas,
    //          2 = delete a canvas, invalid input otherwise
    private void processDrawCommand(String command) {
        if (command.equals("1")) {
            editCanvas();
        } else if (command.equals("2")) {
            System.out.println("Type canvas position on list to DELETE");
            int index = stringToInt(input.next());
            drawroom.deleteCanvas(index);
        } else {
            System.out.println(INVALIDTEXT);
        }
    }

    // EFFECTS: processes commands from main menu, 1 = go to canvases,
    //          2 = go to cases, invalid otherwise
    private void processMainCommand(String command) {
        if (command.equals("1")) {
            runDrawMenu();
        } else if (command.equals("2")) {
            runCasesMenu();
        } else if (command.equals("3")) {
            saveBrushesRoom();
            saveDrawingRoom();
            System.out.println("Saving Complete!");
        } else if (command.equals("4")) {
            loadBrushesRoom();
            loadDrawingRoom();
            System.out.println("Loading Complete!");
        } else if (command.equals("5")) {
            reset();
        } else {
            System.out.println(INVALIDTEXT);
        }
    }

    // EFFECTS: processes commands from cases menu, 1 = make a case,
    //          2 = delete a case, type EXACT name of case to look
    //          inside, invalid otherwise
    private void processCasesCommand(String command) {
        if (command.equals("1")) {
            System.out.println("Name your case");
            String name = input.next();
            brushroom.getCases().add(new PencilCase(name));
        } else if (command.equals("2")) {
            System.out.println("Type case to DELETE");
            String caseToDelete = input.next();
            brushroom.deleteCase(caseToDelete);
        } else if (brushroom.isNameInListCases(command)) {
            runBrushesMenu(command);
        } else {
            System.out.println(INVALIDTEXT);
        }
    }

    // EFFECTS: processes commands from brush menu, 1 = make a brush,
    //          2 = delete a brush, type EXACT name of brush to look
    //          or edit, invalid otherwise
    private void processBrushesCommand(String command, PencilCase pencilCase) {
        if (command.equals("1")) {
            editBrush(pencilCase);
        } else if (command.equals("2")) {
            System.out.println("Type brush to DELETE");
            String brushToDelete = input.next();
            brushroom.deleteBrush(brushToDelete, pencilCase);
        } else if (brushroom.isNameInListBrushes(command, pencilCase)) {
            displayEditMenu(command, pencilCase);
            String edit = input.next();
            processEditCommand(edit, pencilCase.getBrushWithName(command));
        } else {
            System.out.println(INVALIDTEXT);
        }
    }

    // EFFECTS: processes commands from brush menu, 1 = edit brush color,
    //          2 = edit brush opacity, 3 = edit brush name, 4 = edit
    //          brush size
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

    // REQUIRES: inputs are either Strings or int
    // MODIFIES: pencilCase and brush
    // EFFECTS:  creates new brush with input from user and puts brush
    //           into pencilCase
    private void editBrush(PencilCase pencilCase) {
        Brush brush = new Brush(10, "default", 0,0,0,1);
        System.out.println("Set brush name:");
        brush.setName(input.next());
        editColor(brush);
        System.out.println("Set brush size:");
        brush.setSize(stringToInt(input.next()));
        System.out.println("Set brush opacity:");
        brush.setOpacity(stringToInt(input.next()));
        pencilCase.addBrush(brush);
    }

    // MODIFIES: brush
    // EFFECTS:  edits RGB color values of given brush by taking in
    //           user input
    private void editColor(Brush brush) {
        System.out.println("Type red value [0 - 255]");
        brush.setRed(stringToInt(input.next()));
        System.out.println("Type green value [0 - 255]");
        brush.setGreen(stringToInt(input.next()));
        System.out.println("Type blue value [0 - 255]");
        brush.setBlue(stringToInt(input.next()));
    }

    // MODIFIES: canvases
    // EFFECTS:  makes new canvas from user input
    public void editCanvas() {
        System.out.println("Canvas type (blank or photo)");
        String type = input.next();
        System.out.println("Width:");
        int width = stringToInt(input.next());
        System.out.println("Height:");
        int height = stringToInt(input.next());
        drawroom.getCanvases().add(new Canvas(type, height, width));
    }

    // EFFECTS: displays draw menu and options in console based ui
    private void displayDrawMenu() {
        System.out.println("CANVAS MENU");
        if (drawroom.getCanvases().isEmpty()) {
            System.out.println("No canvases...");
        } else {
            for (Canvas c : drawroom.getCanvases()) {
                System.out.println(c.getWidth() + "x" + c.getHeight() + "-" + c.getType());
            }
        }
        System.out.println("\t(1) Make a canvas");
        System.out.println("\t(2) Delete a canvas");
        System.out.println(BACKTEXT);
    }

    // EFFECTS: displays main menu and options in console based ui
    private void displayMainMenu() {
        System.out.println("MAIN MENU");
        System.out.println("\t(1) Draw on a new canvas");
        System.out.println("\t(2) Look at brushes");
        System.out.println("\t(3) Save all changes");
        System.out.println("\t(4) Load from save");
        System.out.println("\t(5) Reset all save data");
        System.out.println("\tPress 'q' to quit");
    }

    // EFFECTS: displays case menu and options in console based ui
    private void displayCasesMenu() {
        System.out.println("CASES MENU");
        System.out.println("Your cases:");
        if (brushroom.getCases().isEmpty()) {
            System.out.println("You have no cases.");
        } else {
            for (PencilCase c : brushroom.getCases()) {
                System.out.println(c.getName());
            }
        }
        System.out.println("\t(1) Make a case");
        System.out.println("\t(2) Delete a case");
        System.out.println("\tType case name (case sensitive) to open");
        System.out.println(BACKTEXT);
    }

    // EFFECTS: displays brush menu and options in console based ui
    private void displayBrushesMenu(PencilCase pencilCase) {
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
        System.out.println(BACKTEXT);
    }

    // EFFECTS: displays edit menu and options in console based ui
    private void displayEditMenu(String name, PencilCase pencilCase) {
        String red = String.valueOf(pencilCase.getBrushWithName(name).getRed());
        String green = String.valueOf(pencilCase.getBrushWithName(name).getGreen());
        String blue = String.valueOf(pencilCase.getBrushWithName(name).getBlue());
        System.out.println("EDIT MENU");
        System.out.println("\t(1) - Color = " + red + "-" + green + "-" + blue);
        System.out.println("\t(2) - Opacity = " + pencilCase.getBrushWithName(name).getOpacity());
        System.out.println("\t(3) - Name = " + name);
        System.out.println("\t(4) - Size = " + pencilCase.getBrushWithName(name).getSize());
        System.out.println(BACKTEXT);
    }

    // EFFECTS: initialize input, cases, and canvases
    private void initialize() {
        input = new Scanner(System.in);
        brushroom = new BrushesRoom();
        drawroom = new DrawingRoom();
        emptyBrushRoom = new BrushesRoom();
        emptyDrawingRoom = new DrawingRoom();
        jsonWriterBrushesRoom = new JsonWriter(JSON_STORE_BR);
        jsonReaderBrushesRoom = new JsonReader(JSON_STORE_BR);
        jsonWriterDrawingRoom = new JsonWriter(JSON_STORE_DR);
        jsonReaderDrawingRoom = new JsonReader(JSON_STORE_DR);
        loadBrushesRoom();
    }

    // EFFECTS: prevent exception, try to turn string to int but if exception is thrown,
    //          use default number 10
    private int stringToInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            // default size
            return 50;
        }
    }

    private void saveBrushesRoom() {
        try {
            jsonWriterBrushesRoom.open();
            jsonWriterBrushesRoom.writeBrushRoom(brushroom);
            jsonWriterBrushesRoom.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_BR);
        }
    }

    private void loadBrushesRoom() {
        try {
            brushroom = jsonReaderBrushesRoom.readBrushesRoom();
        } catch (IOException e) {
            System.out.println("Unable to load file: " + JSON_STORE_BR);
        }
    }

    private void saveDrawingRoom() {
        try {
            jsonWriterDrawingRoom.open();
            jsonWriterDrawingRoom.writeDrawingRoom(drawroom);
            jsonWriterDrawingRoom.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_DR);
        }
    }

    private void loadDrawingRoom() {
        try {
            drawroom = jsonReaderDrawingRoom.readDrawingRoom();
        } catch (IOException e) {
            System.out.println("Unable to load file: " + JSON_STORE_DR);
        }
    }

    private void reset() {
        try {
            jsonWriterBrushesRoom.open();
            jsonWriterBrushesRoom.writeBrushRoom(emptyBrushRoom);
            jsonWriterBrushesRoom.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_BR);
        }
        try {
            jsonWriterDrawingRoom.open();
            jsonWriterDrawingRoom.writeDrawingRoom(emptyDrawingRoom);
            jsonWriterDrawingRoom.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_DR);
        }
    }
}
