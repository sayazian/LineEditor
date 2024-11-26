import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    //    public static String filePath = "/Users/sahar/IdeaProjects/LineEditor/src/file.txt";
    public static int currentLineNumber = 1;
    public static String[] fileLines = new String[100];
    public static String tempFilePath = "/Users/sahar/IdeaProjects/LineEditor/src/temp.txt";

    public static void main(String[] args) throws FileNotFoundException {
        String filePath = args[0];
        readFileLines(filePath);
        boolean continued = true;
        showOptions();
        while (continued) {
            String userInput = readInput();
            continued = runAction(userInput, filePath);
        }
    }

    private static void readFileLines(String filePath) throws FileNotFoundException {
        File theFile = new File(filePath);
        Scanner input = new Scanner(theFile);
        for (int i = 0; i < fileLines.length; i++) {
            if (input.hasNextLine()) fileLines[i] = input.nextLine();
        }
    }

    private static boolean runAction(String userInput, String filePath) throws FileNotFoundException {
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
                tryQuitCommand(filePath);
                continued = false;
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
        System.out.println("The current line is: " + currentLineNumber);
        System.out.println("The number of lines is: " + getNumberOfValidLines(fileLines));
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
        if (!substituteCommandIsValid(input)) {
            System.out.println("Invalid input for substitute.");
        }
        else {
            String[] inputs = getSubstituteStrings(input);
            runSubstituteCommand(fileLines, currentLineNumber, inputs[0], inputs[1]);
        }
    }

    public static boolean substituteCommandIsValid(String input) {
        int numberOfFreeSlashes = 0;
        int index = 0;
        boolean lastThingSeenIsSlash = false;

        while (index < input.length()) {
            if (input.charAt(index) == '/') {
                numberOfFreeSlashes++;
                lastThingSeenIsSlash = true;
            } else if (input.charAt(index) == '\\') {
                index++;
                lastThingSeenIsSlash = false;
            }
            index++;
        }
        if ((numberOfFreeSlashes == 3) && lastThingSeenIsSlash) {
            return true;
        } else return false;
    }

    public static String[] getSubstituteStrings(String input) {
        int[] indexes = new int[input.length()];
        String[] substituteStrings = new String[2];
        StringBuilder newInput = new StringBuilder();
        int pos = 0;
        int index = 0;
        while (index < input.length()) {
            if (input.charAt(index) == '/') {
                indexes[pos] = index;
                pos++;
            } else if (input.charAt(index) == '\\') {
                index++;
            }
            index++;
        }
        substituteStrings[0] = removeExtraBackSlashes(input.substring(indexes[0] + 1, indexes[1]));
        substituteStrings[1] = removeExtraBackSlashes(input.substring(indexes[1] + 1, indexes[2]));
        return substituteStrings;
    }

    public static void runSubstituteCommand(String[] fileLines, int currentLineNumber, String oldString, String newString) {
        String newOldString = oldString;
        String newNewString = newString;
        if (oldString.indexOf('\\') >= 0) newOldString = removeExtraBackSlashes(oldString);
        if (newString.indexOf('\\') >= 0) newNewString = removeExtraBackSlashes(newString);

        if (fileLines[currentLineNumber - 1].contains(newOldString)) {
            fileLines[currentLineNumber - 1] = fileLines[currentLineNumber - 1].replace(newOldString, newNewString);
            System.out.println("Current Line: " + fileLines[currentLineNumber - 1]);
        } else {
            System.out.println("The input String \"" + oldString + "\" does not exist in the current line.");
        }
    }

    public static String removeExtraBackSlashes(String input) {
        StringBuilder newInput = new StringBuilder();
        int index = 0;

        while (index < input.length()) {
            if (input.charAt(index) == '\\') {
                index++;
            } else newInput.append(input.toCharArray()[index]);
            index++;
        }
        return newInput.toString();
    }

    private static void tryCopyCommand(String input) {
        int number = getLineNumber(input);
        if (number > 0) runCopyCommand(number);
        else System.out.println("Invalid input for \"Copy\".");
    }

    private static void runCopyCommand(int number) {
        runCopyCommand(fileLines, currentLineNumber, number);
    }

    public static void runCopyCommand(String[] fileLines, int currentLineNumber, int number) {
        try {
            try (PrintWriter clipboard = new PrintWriter(tempFilePath)) {
                String[] newLines = returnTheCopyLines(fileLines, currentLineNumber, number);
                clipboard.print(Arrays.toString(newLines));
                currentLineNumber = currentLineNumber + number;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static String[] returnTheCopyLines(String[] fileLines, int currentLineNumber, int number) {
        return new String[]{};
    }

    public static void tryLocateCommand(String input) {
        String[] inputs = getSubstituteStrings(input);
        boolean hasTwoStrings = inputs.length == 2;
        boolean hasTerminalSlash = input.charAt(input.length() - 1) == '/';
        if (!hasTwoStrings || !hasTerminalSlash) System.out.println("Invalid input for locate.");
        else runLocateCommand(inputs[1]);
    }

    private static void runLocateCommand(String input) {
        int lineNumber = runLocateCommand(fileLines, currentLineNumber, input);
        if (lineNumber < 0) {
            System.out.println("The input String does not exist after this point.");
        } else {
            System.out.println("The input String occurs in line " + lineNumber);
            currentLineNumber = lineNumber;
            System.out.println(fileLines[currentLineNumber - 1]);
        }
    }

    public static int runLocateCommand(String[] fileLines, int currentLineNumber, String input) {
        int validLines = getNumberOfValidLines(fileLines);
        for (int i = currentLineNumber - 1; i < validLines; i++) {
            if (fileLines[i].contains(input)) return i + 1;
        }
        return -1;
    }

    public static void tryDeleteCommand(String input) {
        int number = getLineNumber(input);
        int validNumber = getNumberOfValidLines(fileLines);
        if (currentLineNumber + number > validNumber) {
            System.out.println("The number is too big. The file has " + validNumber + " lines.");
            return;
        }
        if (number > 0) runDeleteCommand(number);
        else System.out.println("Invalid input for \"Delete\".");
    }

    private static void runDeleteCommand(int number) {
        runDeleteCommand(fileLines, currentLineNumber, number);
    }

    public static void runDeleteCommand(String[] fileLines, int currentLineNumber, int number) {
        int validLines = getNumberOfValidLines(fileLines);
        int newValidLines = validLines - number;
        System.arraycopy(fileLines, currentLineNumber + number - 1, fileLines, currentLineNumber - 1, newValidLines - currentLineNumber + 1);
        Arrays.fill(fileLines, newValidLines, fileLines.length, null);
    }

    public static void tryMoveCommand(String input) {
        int number = getMoveLineNumber(input);
        if (number == 200) System.out.println("Invalid input for \"Move\".");
        else runMoveCommand(number);
    }

    private static void runMoveCommand(int number) {
        currentLineNumber = currentLineNumber + number;
        if ((currentLineNumber < 1) || (currentLineNumber > 100)) {
            currentLineNumber = currentLineNumber - number;
            System.out.println("Destination is out of range.");
            return;
        }
        if (!(fileLines[currentLineNumber - 1] == null)) {
            System.out.println(fileLines[currentLineNumber - 1]);

        } else {
            System.out.println("The line #" + currentLineNumber + " is empty.");
            currentLineNumber = currentLineNumber - number;
        }
    }

    public static void tryTypeCommand(String input) {
        int number = getLineNumber(input);
        int validNumber = getNumberOfValidLines(fileLines);
        if (currentLineNumber + number - 1 > validNumber) {
            System.out.println("The number is too big. The file has " + validNumber + " lines.");
            return;
        }
        if (number > 0) runTypeCommand(number);
        else System.out.println("Invalid input for \"Type\".");
    }

    private static void runTypeCommand(int number) {
        int typedLines = 0;
        if ((currentLineNumber + number) < fileLines.length) {
            for (int i = currentLineNumber - 1; i <= currentLineNumber + number - 2; i++) {
                if (!(fileLines[i] == null)) {
                    System.out.println(fileLines[i]);
                    typedLines++;
                }
                if (fileLines[i] == null) {
                    System.out.println("*** Here is the end of the file. ***");
                    break;
                }
            }
            currentLineNumber = currentLineNumber + typedLines - 1;

        } else {
            System.out.println("The number is too large.");
        }
    }

    private static int getNumberOfValidLines(String[] fileLines) {
        int validLines = 0;
        for (String fileLine : fileLines) {
            if (!(fileLine == null)) validLines++;
            if (fileLine == null) break;
        }
        return validLines;
    }

    public static void tryPasteCommand() {
        System.out.println("You have chosen \"Paste\".");

    }

    public static void tryInsertCommand(String input) {
        int number = getLineNumber(input);
        int validLines = getNumberOfValidLines(fileLines);
        if ((number > 0) && (number <= fileLines.length - validLines)) runInsertCommand(number);
        else System.out.println("Invalid input for \"Insert\".");
    }

    private static void runInsertCommand(int number) {
        String[] newLines = enterInsertLines(number);
        insertTheNewLines(newLines);
        currentLineNumber = currentLineNumber + number - 1;
    }

    private static void insertTheNewLines(String[] newLines) {
        insertTheNewLines(fileLines, newLines, currentLineNumber);
    }

    public static void insertTheNewLines(String[] fileLines, String[] newLines, int currentLineNumber) {
        int validLines = getNumberOfValidLines(fileLines);
        String[] temp = Arrays.copyOfRange(fileLines, currentLineNumber - 1, validLines);
        if ((currentLineNumber + newLines.length) < fileLines.length) {
            System.arraycopy(newLines, 0, fileLines, currentLineNumber - 1, newLines.length);
            System.arraycopy(temp, 0, fileLines, currentLineNumber + newLines.length - 1, validLines - currentLineNumber + 1);
        } else {
            System.arraycopy(newLines, 0, fileLines, currentLineNumber - 1, fileLines.length - currentLineNumber + 1);
        }
    }

    public static String[] enterInsertLines(int number) {
        Scanner input = new Scanner(System.in);
        String[] newLines = new String[number];
        System.out.print("Please enter " + number + " lines > ");
        for (int i = 0; i < number; i++) {
            newLines[i] = input.nextLine();
        }
        return newLines;
    }

    public static void tryReplaceCommand(String input) {
        int number = getLineNumber(input);
        int validLines = getNumberOfValidLines(fileLines);
        if ((number > -1) && (number <= validLines - currentLineNumber + 1)) runReplaceCommand(number);
        else System.out.println("Invalid input for \"Replace\".");
    }

    public static void runReplaceCommand(int number) {
        String[] newLines = enterInsertLines(number);
        replaceWithNewLines(newLines);
        currentLineNumber = currentLineNumber + number - 1;
    }

    private static void replaceWithNewLines(String[] newLines) {
        replaceWithNewLines(fileLines, newLines, currentLineNumber);
    }

    public static void replaceWithNewLines(String[] fileLines, String[] newLines, int currentLineNumber) {
        if (newLines.length <= (fileLines.length - currentLineNumber + 1)) {
            System.arraycopy(newLines, 0, fileLines, currentLineNumber - 1, newLines.length);
        } else {
            System.out.println("Invalid number of lines.");
        }
    }

    public static void tryQuitCommand(String filePath) throws FileNotFoundException {
        System.out.println("You have chosen \"Quit\".");
        saveTheFile(filePath);
    }

    private static void saveTheFile(String filePath) throws FileNotFoundException {
        PrintWriter output = new PrintWriter(filePath);
        for (String fileLine : fileLines) if (!(fileLine == null)) output.println(fileLine);
        output.close();
    }

    private static int getLineNumber(String input) {
        String[] inputs = input.split(" ");
        if (inputs.length != 2) return -1;
        return isNumeric(inputs[1]) ? Integer.parseInt(inputs[1]) : -1;
    }

    private static int getMoveLineNumber(String input) {
        String[] inputs = input.split(" ");
        int start = 0;
        int sign = 1;
        if (inputs.length != 2) return 200;
        if (isNumericSigned(inputs[1])) {
            if (inputs[1].charAt(0) == '-') {
                start = 1;
                sign = -1;
            }
            return sign * Integer.parseInt(inputs[1].substring(start));
        } else return 200;
    }

    public static boolean isNumeric(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) < '0' || input.charAt(i) > '9') return false;
        }
        return true;
    }

    public static boolean isNumericSigned(String input) {
        int start = 0;
        if (input.charAt(0) == '-') start = 1;
        return isNumeric(input.substring(start));
    }
}