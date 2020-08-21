import java.util.Scanner;

public class Duke {
    final static String BREAK = System.lineSeparator();
    final static String TAB = "    ";
    final static String BORDER = "    ____________________________________________________________";

    static TaskManager taskManager;

    public static void main(String[] args) {
        String logo = " ____            _\n"
                + "|  _ \\ _   _   _| | ___\n"
                + "| | | | | | |/ _  |/ _ \\\n"
                + "| |_| | |_| | |_| |  __/\n"
                + "|____/ \\__,_|\\____|\\___|";
        System.out.println("Hello from\n" + logo);
        System.out.println(BORDER);
        greet();

        // create input scanner
        Scanner in = new Scanner(System.in);
        interact(in);
        in.close();

        farewell();
    }

    private static void interact(Scanner in) {
        taskManager = new TaskManager();
        while (in.hasNextLine()) {
            String userInputLine = in.nextLine();

            if (userInputLine.matches("done [0-9]")) {
                if (taskManager.checkedTask(userInputLine) > 0) {
                    String desc = TAB + "Nice! I've marked this task as done:";
                    printWithWrappingBorders(desc + BREAK + taskManager.tasksToString());
                }
            } else if (userInputLine.equalsIgnoreCase("bye")) {
                break;
            } else if (userInputLine.equalsIgnoreCase("list")) {
                String desc = TAB + "Here are the tasks in your list:";
                printWithWrappingBorders(desc + BREAK + taskManager.tasksToString());
            } else {
                taskManager.addTask(userInputLine);
                printWithWrappingBorders(TAB + "added: " + userInputLine + BREAK);
            }
        }
    }

    private static void greet() {
        String greeting = TAB + "Hello! I'm Dude" + BREAK
                + TAB + "What can I do for you?";
        System.out.println(greeting);
        System.out.println(BORDER);
    }


    private static void farewell() {
        String farewell = TAB + "Bye. Hope to see you again soon!" + BREAK;
        printWithWrappingBorders(farewell);
    }

    private static void printWithWrappingBorders(String lineBlock) {
        System.out.println(BORDER);
        System.out.print(lineBlock);
        System.out.println(BORDER);
    }
}
