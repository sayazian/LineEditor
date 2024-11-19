import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean continued = true;
        showOptions();
        while (continued) {
            String userInput = readInputs();
            continued = runAction(userInput);
        }
    }

    private static boolean runAction(String userInput) {
        char currentCommand = getCurrentCommand(userInput);
        boolean continued = true;

        switch (currentCommand) {
            case 's':
                substituteCmd(userInput);
                break;
            case 'c':
                copyCmd(userInput);
                break;
            case 'l':
                locateCmd(userInput);
                break;
            case 'd':
                deleteCmd(userInput);
                break;
            case 'm':
                moveCmd(userInput);
                break;
            case 't':
                typeCmd(userInput);
                break;
            case 'p':
                pasteCmd();
                break;
            case 'i':
                insertCmd(userInput);
                break;
            case 'r':
                replaceCmd(userInput);
                break;
            case 'q':
                quitCmd();
                continued = false;
                break;
        }
        return continued;
    }


    private static char getCurrentCommand(String userInput) {
        return userInput.toLowerCase().charAt(0);
    }

    public static String readInputs() {
        String refString = "scldmtpirq";
        boolean choiceIsNotValid = true;
        String[] inputs = new String[]{};
        String userInput = "";

        while (choiceIsNotValid) {
            userInput = readInput();
            char inputChar = userInput.toLowerCase().charAt(0);
            if (refString.indexOf(inputChar) < 0) {
                System.out.println("Invalid choice.");
            } else {
                choiceIsNotValid = false;
            }
        }
        return userInput;
    }

    public static String readInput() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please make a choice > ");
        return input.nextLine();
    }

    private static void showOptions() {
        System.out.println("Line Editor");
        System.out.println("Substitute/oldstring/newstring/\t\tType #");
        System.out.println("Copy #\t\t\t\t\t\t\t\tPaste");
        System.out.println("Locate/string/\t\t\t\t\t\tInsert #");
        System.out.println("Delete #\t\t\t\t\t\t\tReplace #");
        System.out.println("Move #\t\t\t\t\t\t\t\tQuit");

    }

    public static void substituteCmd(String input) {
        System.out.println("you have chosen \"Substitue\".");
        String[] inputs = input.split("/");
        if (inputs.length != 3) {
            System.out.println("Invalid input for locate.");
        } else {
            System.out.println("you have chosen \"Locate\" with inputs: \"" + inputs[1] + "\" & \"" + inputs[2] + "\".");
        }
    }

    private static void copyCmd(String input) {
        int number = getLineNumber(input);
        if (number > 0) {
            System.out.println("you have chosen to \"Copy\" line #" + number + ".");
        }
    }

    public static void locateCmd(String input) {
        String[] inputs = input.split("/");
        if (inputs.length != 2) {
            System.out.println("Invalid input for locate.");
        } else {
            System.out.println("you have chosen \"Locate\" with input: \"" + inputs[1] + "\".");
        }
    }

    public static void deleteCmd(String input) {
        int number = getLineNumber(input);
        if (number > 0) {
            System.out.println("You have chosen to \"Delete\" line #" + number + ".");
        }
    }

    public static void moveCmd(String input) {
        int number = getLineNumber(input);
        if (number > 0) {
            System.out.println("You have chosen to \"Move\" line #" + number + ".");
        }
    }

    public static void typeCmd(String input) {
        int number = getLineNumber(input);
        if (number > 0) {
            System.out.println("You have chosen to \"Type\" line #" + number + ".");
        }
    }

    public static void pasteCmd() {
        System.out.println("You have chosen \"Paste\".");

    }

    public static void insertCmd(String input) {
        int number = getLineNumber(input);
        if (number > 0) {
            System.out.println("You have chosen to \"Insert\" line #" + number + ".");
        }
    }

    public static void replaceCmd(String input) {
        int number = getLineNumber(input);
        if (number > 0) {
            System.out.println("You have chosen to \"Replace\" line #" + number + ".");
        }
    }

    public static void quitCmd() {
        System.out.println("You have chosen \"Quit\".");
    }

    private static int getLineNumber(String input) {
        String[] inputs = input.split(" ");
        if (inputs.length != 2) {
            System.out.println("Invalid line number.");
            return -1;
        }
        return isNumeric(inputs[1]);
    }

    private static int isNumeric(String input) {
        char[] inputs = input.toCharArray();
        String numbers = "0123456789";

        for (int i = 0; i < input.length(); i++) {
            if (numbers.indexOf(inputs[i]) < 0) {
                System.out.println("The line number is invalid.");
                return -1;
            }
        }
        return Integer.parseInt(input);
    }

}