import java.util.Scanner;

public class Duke {
    public static final String HALF_TAB = "  ";
    static final String BREAK = System.lineSeparator();
    static final String TAB = "    ";
    static final String BORDER = "    ____________________________________________________________";

    private static TaskManager taskManager;

    public static void main(String[] args) {
        showLogo();
        greet();
        interact();
        sayBye();
    }

    private static void interact() {
        // create input scanner
        Scanner in = new Scanner(System.in);

        taskManager = new TaskManager();
        while (in.hasNextLine()) {
            String userInputLine = in.nextLine();

            if (inputIsBye(userInputLine)) {
                break;
            } else if (inputIsList(userInputLine)) {
                listTasks("Here are the tasks in your list:");
            } else if (inputIsDone(userInputLine)) {
                Task doneTask = taskManager.taskChecked(userInputLine);
                if (taskIsChecked(doneTask)) {
                    listDoneTask("Nice! I've marked this task as done:", doneTask);
                }
            } else {
                Task taskAdded = taskManager.addTask(userInputLine);
                showAddTask(taskAdded);
            }
        }

        in.close();
    }

    private static void showAddTask(Task task) {
        String prologue = TAB + "Got it. I've added this task:" + BREAK;
        String content = TAB + HALF_TAB + task.toString() + BREAK;
        String epilogue = TAB + "Now you have " + taskManager.getTotalTasksNumber() + " tasks in the list." + BREAK;
        printBetwBorders(prologue + content + epilogue);
    }

    private static boolean taskIsChecked(Task doneTask) {
        return doneTask != null;
    }

    private static void listTasks(String s) {
        String desc = TAB + s;
        printBetwBorders(desc + BREAK + taskManager.tasksToString());
    }

    private static void listDoneTask(String s, Task doneTask) {
        String desc = TAB + s;
        printBetwBorders(desc + BREAK
                        + TAB + HALF_TAB + doneTask + BREAK);
    }

    private static boolean inputIsDone(String userInputLine) {
        return userInputLine.matches("done ([0-9]+)");
    }

    private static boolean inputIsList(String userInputLine) {
        return userInputLine.equalsIgnoreCase("list");
    }

    private static boolean inputIsBye(String userInputLine) {
        return userInputLine.equalsIgnoreCase("bye");
    }

    private static void showLogo() {
        String logo = " ____            _\n"
                + "|  _ \\ _   _   _| | ___\n"
                + "| | | | | | |/ _  |/ _ \\\n"
                + "| |_| | |_| | |_| |  __/\n"
                + "|____/ \\__,_|\\____|\\___|";
        System.out.println("Hello from\n" + logo);
        System.out.println(BORDER);
    }

    private static void greet() {
        String greeting = TAB + "Hello! I'm Dude" + BREAK
                + TAB + "What can I do for you?";
        System.out.println(greeting);
        System.out.println(BORDER);
    }

    private static void sayBye() {
        String sayBye = TAB + "Bye. Hope to see you again soon!" + BREAK;
        printBetwBorders(sayBye);
    }

    private static void printBetwBorders(String lineBlock) {
        System.out.println(BORDER);
        System.out.print(lineBlock);
        System.out.println(BORDER);
    }
}
