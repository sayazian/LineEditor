import java.util.Scanner;

public class Main {

    private static void lineEditorMainPage() {

        String[] inputs = findInputs();
        char currentCommand = inputs[0].charAt(0);

        switch (currentCommand) {
            case 's':
                substituteCmd(inputs[1], inputs[2]);
            case 'l':
                locateCmd(inputs[1]);
            case 'd':
                deleteCmd(inputs[1]);
            case 'm':
                moveCmd(inputs[1]);
            case 't':
                typeCmd(inputs[1]);
            case 'p':
                pasteCmd();
            case 'i':
                insertCmd(inputs[1]);
            case 'r':
                replaceCmd(inputs[1]);
            case 'q':
                quitCmd();
        }
    }

    private static String[] findInputs() {
        String refString = "sldmtpirq";
        boolean choiceIsNotValid = true;
        String[] inputs = new String[]{};

        while (choiceIsNotValid) {
            String userInput = enterInputs();
            inputs = userInput.split(" ");
            if (refString.indexOf(inputs[0].charAt(0)) < 0 ) {
                System.out.println("Invalid choice.");
            } else {
                choiceIsNotValid = false;
            }
        }
        return inputs;
    }

    public static String enterInputs() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    private static void substituteCmd(String input, String input1) {

    }

    private static void locateCmd(String input) {

    }

    private static void deleteCmd(String input) {

    }

    private static void moveCmd(String input) {
    }

    private static void typeCmd(String input) {

    }

    private static void pasteCmd() {

    }

    private static void insertCmd(String input) {

    }

    private static void replaceCmd(String input) {
    }

    private static void quitCmd() {
    }
}