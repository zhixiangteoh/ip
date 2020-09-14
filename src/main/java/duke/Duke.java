package duke;

import duke.exception.ParseException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDescriptionException;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    public static final String HALF_TAB = "  ";
    public static final String BREAK = System.lineSeparator();
    public static final String TAB = "    ";
    public static final String BORDER = "    ____________________________________________________________";
    public static final String FILE_PATH = "./data/duke.txt";
    public static final String FOLDER_PATH = "./data";

    private static TaskManager taskManager;
    private static File file;
    private static File folder;

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

        file = new File(FILE_PATH);
        folder = new File(FOLDER_PATH);

        if (!folder.exists()) {
            System.out.println("Creating new folder 'data' in root directory:");
            folder.mkdir();
        }

        if (file.exists()) {
            try {
                taskManager.readFile(file);
            } catch (FileNotFoundException fnfe) {
                System.out.println(fnfe.getMessage());
            } catch (ParseException pe) {
                System.out.println(pe.getMessage());
            }
        }

        while (in.hasNextLine()) {
            String userInputLine = in.nextLine();

            if (commandIsBye(userInputLine)) {
                break;
            } else if (commandIsList(userInputLine)) {
                listTasks("Here are the tasks in your list:");
                // returns task that is checked if a task is successfully checked, else returns null
            } else if (commandIsDone(userInputLine)) {
                Task doneTask = taskManager.taskChecked(userInputLine);
                if (taskIsChecked(doneTask)) {
                    listDoneTask("Nice! I've marked this task as done:", doneTask);
                }
            } else if (commandIsDelete(userInputLine)) {
                Task deletedTask = taskManager.taskDeleted(userInputLine);
                if (taskIsDeleted(deletedTask)) {
                    listTask("Noted. I've removed this task:", deletedTask);
                }
            } else {
                try {
                    // returns task that is added if a task is successfully added, else returns null
                    Task addedTask = taskManager.addTask(userInputLine);
                    listTask("Got it. I've added this task:", addedTask);
                } catch (InvalidDescriptionException ide) {
                    showInvalidDescMessage(ide);
                } catch (InvalidCommandException ice) {
                    showInvalidCommandMessage();
                }
            }
        }

        in.close();
    }

    private static void showInvalidCommandMessage() {
        String message = TAB + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + BREAK;
        printBetwBorders(message);
    }

    private static void showInvalidDescMessage(InvalidDescriptionException ide) {
        String message = TAB + "☹ OOPS!!! The description of a(n) "
                + ide.getTaskType() + " cannot be empty." + BREAK;
        printBetwBorders(message);
    }

    private static void listTask(String message, Task task) {
        String prologue = TAB + message + BREAK;
        String content = TAB + HALF_TAB + task.toString() + BREAK;
        String epilogue = TAB + "Now you have " + taskManager.getTotalTasksNumber() + " tasks in the list." + BREAK;
        printBetwBorders(prologue + content + epilogue);
    }

    private static boolean taskIsChecked(Task doneTask) {
        return doneTask != null;
    }

    private static boolean taskIsDeleted(Task deletedTask) {
        return deletedTask != null;
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

    private static boolean commandIsDone(String userInputLine) {
        return userInputLine.matches("done ([0-9]+)");
    }

    private static boolean commandIsList(String userInputLine) {
        return userInputLine.equalsIgnoreCase("list");
    }

    private static boolean commandIsBye(String userInputLine) {
        return userInputLine.equalsIgnoreCase("bye");
    }

    private static boolean commandIsDelete(String userInputLine) {
        return userInputLine.matches("delete ([0-9]+)");
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

    public static void printBetwBorders(String lineBlock) {
        System.out.println(BORDER);
        System.out.print(lineBlock);
        System.out.println(BORDER);
    }
}
