import java.util.Scanner;

public class Duke {
    static final String BREAK = System.lineSeparator();
    static final String TAB = "    ";
    static final String BORDER = "    ____________________________________________________________";

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

            if (inputIsBye(userInputLine)) {
                break;
            } else if (inputIsList(userInputLine)) {
                listTasks("Here are the tasks in your list:");
            } else if (inputIsDone(userInputLine) && taskIsChecked(userInputLine)) {
                listTasks("Nice! I've marked this task as done:");
            } else {
                taskManager.addTask(userInputLine);
                showAddTask(userInputLine);
            }
        }
    }

    private static void showAddTask(String userInputLine) {
        printBetwBorders(TAB + "Got it. I've added this task: " + BREAK
                + userInputLine + BREAK);
    }

    private static boolean taskIsChecked(String userInputLine) {
        return taskManager.checkTask(userInputLine) > 0;
    }

    private static void listTasks(String s) {
        String desc = TAB + s;
        printBetwBorders(desc + BREAK + taskManager.tasksToString());
    }

    private static boolean inputIsDone(String userInputLine) {
        return userInputLine.matches("done [0-9]");
    }

    private static boolean inputIsList(String userInputLine) {
        return userInputLine.equalsIgnoreCase("list");
    }

    private static boolean inputIsBye(String userInputLine) {
        return userInputLine.equalsIgnoreCase("bye");
    }

    private static void greet() {
        String greeting = TAB + "Hello! I'm Dude" + BREAK
                + TAB + "What can I do for you?";
        System.out.println(greeting);
        System.out.println(BORDER);
    }

    private static void farewell() {
        String farewell = TAB + "Bye. Hope to see you again soon!" + BREAK;
        printBetwBorders(farewell);
    }

    private static void printBetwBorders(String lineBlock) {
        System.out.println(BORDER);
        System.out.print(lineBlock);
        System.out.println(BORDER);
    }
}
