import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        char currentCommand = ' ';
        showOptions();
        while (currentCommand != 'q') {
            String[] inputs = findInputs();
            currentCommand = getCurrentCommand(inputs);
            chooseAction(inputs);
        }
    }

    private static void chooseAction(String[] inputs) {
        char currentCommand = getCurrentCommand(inputs);
        switch (currentCommand) {
            case 's':
                substituteCmd(inputs[0]);
                break;
            case 'c':
                copyCmd(inputs[0]);
                break;
            case 'l':
                locateCmd(inputs[0]);
                break;
            case 'd':
                deleteCmd(inputs[0]);
                break;
            case 'm':
                moveCmd(inputs[0]);
                break;
            case 't':
                typeCmd(inputs[0]);
                break;
            case 'p':
                pasteCmd();
                break;
            case 'i':
                insertCmd(inputs[0]);
                break;
            case 'r':
                replaceCmd(inputs[0]);
                break;
            case 'q':
                quitCmd();
                break;
        }
    }


    private static char getCurrentCommand(String[] inputs) {
        return inputs[0].toLowerCase().charAt(0);
    }

    public static String[] findInputs() {
        String refString = "scldmtpirq";
        boolean choiceIsNotValid = true;
        String[] inputs = new String[]{};

        while (choiceIsNotValid) {
            String userInput = enterInputs();
            inputs = userInput.split(" ");
            char inputChar = inputs[0].toLowerCase().charAt(0);
            if (refString.indexOf(inputChar) < 0 ) {
                System.out.println("Invalid choice.");
            } else {
                choiceIsNotValid = false;
            }
        }
        return inputs;
    }

    public static String enterInputs() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please make a choice > ");
        return input.nextLine();
    }

    private static void showOptions() {
        System.out.println("Line Editor");
        System.out.println("Substitute/oldstring/newstring/\t\tType #");
        System.out.println("Copy #\t\t\t\t\t\t\t\tPaste #");
        System.out.println("Locate/string/\t\t\t\t\t\tInsert #");
        System.out.println("Delete #\t\t\t\t\t\t\tReplace #");
        System.out.println("Move #\t\t\t\t\t\t\t\tQuit #");

    }

    public static void substituteCmd(String input) {
        System.out.println("you have chosen \"Substitue\".");
    }

    private static void copyCmd(String input) {
        System.out.println("you have chosen \"Copy\".");
    }

    public static void locateCmd(String input) {
        System.out.println("you have chosen \"Locate\".");
    }

    public static void deleteCmd(String input) {
        System.out.println("you have chosen \"Delete\".");
    }

    public static void moveCmd(String input) {
        System.out.println("you have chosen \"Move\".");
    }

    public static void typeCmd(String input) {
        System.out.println("you have chosen \"Type\".");

    }

    public static void pasteCmd() {
        System.out.println("you have chosen \"Paste\".");

    }

    public static void insertCmd(String input) {
        System.out.println("you have chosen \"Insert\".");

    }

    public static void replaceCmd(String input) {
        System.out.println("you have chosen \"Replace\".");
    }

    public static void quitCmd() {
        System.out.println("you have chosen \"Quit\".");
    }
}