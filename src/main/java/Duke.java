import java.util.Scanner;

public class Duke {
    final static String BREAK = System.lineSeparator();
    final static String TAB = "    ";
    final static String BORDER = TAB + "____________________________________________________________";
    final static int MAX_TASKS = 100;

    static Task[] tasks;

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
        tasks = new Task[MAX_TASKS];
        interact(in);
        in.close();

        farewell();
    }

    private static void greet() {
        String greeting = TAB + "Hello! I'm Dude" + BREAK
                        + TAB + "What can I do for you?";
        System.out.println(greeting);
        System.out.println(BORDER);
    }

    private static void interact(Scanner in) {
        boolean isBye = false;
        Task.setTotalTasksNumber(0);
        while (!isBye && in.hasNextLine()) {
            String userInputLine = in.nextLine();

            switch (userInputLine) {
            case "list":
                StringBuilder taskList = new StringBuilder();
                for (Task task : tasks) {
                    if (task != null) {
                        taskList.append(TAB);
                        taskList.append(task.toString());
                        taskList.append(BREAK);
                    }
                }
                printWithWrappingBorders(taskList.toString());
                break;
            case "bye":
                isBye = true;
                break;
            default:
                int taskNumber = Task.getTotalTasksNumber();
                tasks[taskNumber] = new Task(userInputLine, taskNumber);
                printWithWrappingBorders(TAB + "added: " + userInputLine + BREAK);
                break;
            }
        }
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
