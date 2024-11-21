import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static String filePath = "/Users/sahar/IdeaProjects/LineEditor/src/file.txt";
    public static int currentLineNumber = 3;
    public static String[] fileLines = new String[100];

    public static void main(String[] args) throws FileNotFoundException {
        readFileLines(filePath);
        boolean continued = true;
        showOptions();
        while (continued) {
            String userInput = readInput();
            continued = runAction(userInput);
        }
    }

    private static void readFileLines(String filePath) throws FileNotFoundException {
        File theFile = new File(filePath);
        Scanner input = new Scanner(theFile);
        for (int i = 0; i < fileLines.length; i++) {
            if (input.hasNextLine()) fileLines[i] = input.nextLine();
        }
    }

    private static boolean runAction(String userInput) throws FileNotFoundException {
        char currentCommand = getCurrentCommand(userInput);
        boolean continued = true;

        switch (currentCommand) {
            case 's':
                trySubstituteCommand(userInput);
                break;
            case 'c':
                tryCopyCommand(userInput);
                break;
            case 'l':
                tryLocateCommand(userInput);
                break;
            case 'd':
                tryDeleteCommand(userInput);
                break;
            case 'm':
                tryMoveCommand(userInput);
                break;
            case 't':
                tryTypeCommand(userInput);
                break;
            case 'p':
                tryPasteCommand();
                break;
            case 'i':
                tryInsertCommand(userInput);
                break;
            case 'r':
                tryReplaceCommand(userInput);
                break;
            case 'q':
                tryQuitCommand();
                continued = false;
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
        return continued;
    }

    private static char getCurrentCommand(String userInput) {
        return userInput.toLowerCase().charAt(0);
    }

    public static String readInput() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please make a choice > ");
        return input.nextLine();
    }

    private static void showOptions() {
        System.out.println("Line Editor");
        System.out.println("Substitute/old-string/new-string/\t\t\tType #");
        System.out.println("Copy #\t\t\t\t\t\t\t\t\t\tPaste");
        System.out.println("Locate/string/\t\t\t\t\t\t\t\tInsert #");
        System.out.println("Delete #\t\t\t\t\t\t\t\t\tReplace #");
        System.out.println("Move #\t\t\t\t\t\t\t\t\t\tQuit");
    }

    public static void trySubstituteCommand(String input) {
        String[] inputs = input.split("/");
        if ((inputs.length != 3) || (input.charAt(input.length() - 1) != '/'))
            System.out.println("Invalid input for substitute.");
        else runSubstituteCommand(inputs[1], inputs[2]);
    }

    private static void runSubstituteCommand(String substituted, String replacing) {
        System.out.println("You have chosen \"substitute\" with inputs: \"" + substituted + "\" & \"" + replacing + "\".");
    }

    private static void tryCopyCommand(String input) {
        int number = getLineNumber(input);
        if (number > 0) runCopyCommand(number);
        else System.out.println("Invalid input for \"Copy\".");
    }

    private static void runCopyCommand(int number) {
        System.out.println("You have chosen to \"Copy\" line #" + number + ".");
    }

    public static void tryLocateCommand(String input) {
        String[] inputs = input.split("/");
        boolean hasTwoStrings = inputs.length == 2;
        boolean hasTerminalSlash = input.charAt(input.length() - 1) == '/';
        if (!hasTwoStrings || !hasTerminalSlash) System.out.println("Invalid input for locate.");
        else runLocateCommand(inputs[1]);
    }

    private static void runLocateCommand(String input) {
        System.out.println("You have chosen \"Locate\" with input: \"" + input + "\".");
    }

    public static void tryDeleteCommand(String input) {
        int number = getLineNumber(input);
        if (number > 0) runDeleteCommand(number);
        else System.out.println("Invalid input for \"Delete\".");
    }

    private static void runDeleteCommand(int number) {
        System.out.println("You have chosen to \"Delete\" line #" + number + ".");
    }

    public static void tryMoveCommand(String input) {
        int number = getLineNumber(input);
        if (number > 0) runMoveCommand(number);
        else System.out.println("Invalid input for \"Move\".");
    }

    private static void runMoveCommand(int number) {
        currentLineNumber = currentLineNumber + number;
        if (!(fileLines[currentLineNumber - 1] == null)) System.out.println(fileLines[currentLineNumber - 1]);
        else {
            System.out.println("The line #" + currentLineNumber + " is empty.");
            System.out.println("Current line is set to the number before running \"Type\" command.");
            currentLineNumber = currentLineNumber - number;
        }
    }

    public static void tryTypeCommand(String input) {
        int number = getLineNumber(input);
        if (number > 0) runTypeCommand(number);
        else System.out.println("Invalid input for \"Type\".");
    }

    private static void runTypeCommand(int number) {
        for (int i = currentLineNumber - 1; i < currentLineNumber + number - 1; i++) {
            if (!(fileLines[i] == null)) System.out.println(fileLines[i]);
        }
    }

    public static void tryPasteCommand() {
        System.out.println("You have chosen \"Paste\".");

    }

    public static void tryInsertCommand(String input) {
        int number = getLineNumber(input);
        if (number > 0) runInsertCommand(number);
        else System.out.println("Invalid input for \"Insert\".");
    }

    private static void runInsertCommand(int number) {
        String[] newLines = enterInsertLines(number);
        insertTheNewLines(newLines);
        currentLineNumber = currentLineNumber + number;
        printTheFile();
    }

    private static void printTheFile() {
        for (String fileLine : fileLines) {
            if (!(fileLine == null)) System.out.println(fileLine);
        }
        for (String fileLine : fileLines) if (!(fileLine == null)) System.out.println(fileLine);
    }

    private static void insertTheNewLines(String[] newLines) {
        insertTheNewLines(fileLines, newLines, currentLineNumber);
    }

    public static void insertTheNewLines(String[] fileLines, String[] newLines, int currentLineNumber) {
        String[] temp = Arrays.copyOfRange(fileLines, currentLineNumber, fileLines.length);
        if ((currentLineNumber + newLines.length) < fileLines.length) {
            System.arraycopy(newLines, 0, fileLines, currentLineNumber, newLines.length);
            System.arraycopy(temp,0,fileLines,currentLineNumber + newLines.length, fileLines.length - currentLineNumber - newLines.length);
        } else {
            System.arraycopy(newLines, 0, fileLines, currentLineNumber, fileLines.length - currentLineNumber);
        }

    }


    public static String[] enterInsertLines(int number) {
        Scanner input = new Scanner(System.in);
        String[] newLines = new String[number];
        System.out.println("Please enter " + number + " lines > ");
        for (int i = 0; i < number; i++) {
            newLines[i] = input.nextLine();
        }
        System.out.println("You have entered: " + Arrays.toString(newLines));
        return newLines;
    }

    public static void tryReplaceCommand(String input) {
        int number = getLineNumber(input);
        if (number > 0) runReplaceCommand(number);
        else System.out.println("Invalid input for \"Replace\".");
    }

    private static void runReplaceCommand(int number) {
        System.out.println("You have chosen to \"Replace\" line #" + number + ".");
    }

    public static void tryQuitCommand() throws FileNotFoundException {
        System.out.println("You have chosen \"Quit\".");
        saveTheFile();
    }

    private static void saveTheFile() throws FileNotFoundException {
        PrintWriter output = new PrintWriter(filePath);
        for (String fileLine : fileLines) if (!(fileLine == null)) output.println(fileLine);
        output.close();
    }

    private static int getLineNumber(String input) {
        String[] inputs = input.split(" ");
        if (inputs.length != 2) return -1;
        return isNumeric(inputs[1]) ? Integer.parseInt(inputs[1]) : -1;
    }

    public static boolean isNumeric(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) < '0' || input.charAt(i) > '9') return false;
        }
        return true;
    }

}