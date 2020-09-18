package duke;

import duke.exception.InvalidCommandException;
import duke.exception.EmptyDescriptionException;
import duke.exception.TaskIndexNotSpecifiedException;
import duke.task.Task;

import java.util.List;
import java.util.Scanner;

/**
 * User interface of Duke application.
 *
 * Handles all aspects of user input and system output, and executes main logic of application in
 * <kbd>interact</kbd>.
 */
public class Ui {
    public static final String HALF_TAB = "  ";
    public static final String BREAK = System.lineSeparator();
    public static final String TAB = "    ";
    public static final String BORDER = "    ____________________________________________________________";

    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";

    private TaskList tasks;
    private static Parser parser;

    /**
     * Constructor. Initializes Ui object for this TaskList.
     *
     * @param tasks TaskList for the current program
     */
    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints formatted application logo to system output.
     */
    public void showLogo() {
        String logo = " ____            _\n"
                + "|  _ \\ _   _   _| | ___\n"
                + "| | | | | | |/ _  |/ _ \\\n"
                + "| |_| | |_| | |_| |  __/\n"
                + "|____/ \\__,_|\\____|\\___|";
        System.out.println("Hello from\n" + logo);
        System.out.println(BORDER);
    }

    /**
     * Prints formatted greeting to system output.
     */
    public void greet() {
        String greeting = TAB + "Hello! I'm Dude" + BREAK
                + TAB + "What can I do for you?";
        System.out.println(greeting);
        System.out.println(BORDER);
    }

    /**
     * Main execution flow of program.
     *
     * Read in and interpret user command, and outputs relevant information pertaining to specific command.
     */
    public void interact() {
        // create input scanner
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            String userInputLine = in.nextLine();
            parser = new Parser();
            String command = "";
            try {
                command = parser.getCommand(userInputLine);
            } catch (InvalidCommandException ice) {
                showInvalidCommandMessage();
                continue;
            } catch (EmptyDescriptionException ide) {
                showEmptyDescMessage(ide);
                continue;
            }

            switch (command) {
            case COMMAND_BYE:
                return;
            case COMMAND_LIST:
                listTasks("Here are the tasks in your list:", tasks);
                break;
            case COMMAND_DONE:
                try {
                    // returns task that is checked if a task is successfully checked, else returns null
                    Task doneTask = tasks.taskChecked(userInputLine);
                    listDoneTask("Nice! I've marked this task as done:", doneTask);
                } catch (TaskIndexNotSpecifiedException tinse) {
                    showTaskIndexNotSpecifiedMessage();
                }
                break;
            case COMMAND_DELETE:
                try {
                    Task deletedTask = tasks.taskDeleted(userInputLine);
                    listTask("Noted. I've removed this task:", deletedTask);
                } catch (TaskIndexNotSpecifiedException tinse) {
                    showTaskIndexNotSpecifiedMessage();
                }
                break;
            case COMMAND_FIND:
                TaskList tasksFound = new TaskList(tasks.find(userInputLine));
                listTasks("Here are the matching tasks in your list:", tasksFound);
                break;
            default:
                try {
                    // returns task that is added if a task is successfully added, else returns null
                    Task addedTask = tasks.addTask(command, userInputLine);
                    listTask("Got it. I've added this task:", addedTask);
                } catch (InvalidCommandException ice) {
                    showInvalidCommandMessage();
                }
            }
        }

        in.close();
    }

    /**
     * Prints formatted exit statement to system output.
     */
    public void sayBye() {
        String sayBye = TAB + "Bye. Hope to see you again soon!" + BREAK;
        printBetwBorders(sayBye);
    }

    /**
     * Prints formatted message corresponding to a TaskIndexNotSpecifiedException.
     */
    private void showTaskIndexNotSpecifiedMessage() {
        String message = TAB + "☹ OOPS!!! I won't know which task unless you specify an index!" + BREAK;
        printBetwBorders(message);
    }

    /**
     * Prints formatted message corresponding to an InvalidCommandException.
     */
    private void showInvalidCommandMessage() {
        String message = TAB + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + BREAK;
        printBetwBorders(message);
    }

    /**
     * Prints formatted message corresponding to a EmptyDescriptionException, curated for the task type.
     *
     * @param ede EmptyDescriptionException object
     */
    private void showEmptyDescMessage(EmptyDescriptionException ede) {
        String message = TAB + "☹ OOPS!!! The description of a(n) "
                + ede.getTaskType() + " cannot be empty." + BREAK;
        printBetwBorders(message);
    }

    /**
     * Pretty prints a task to system output, prepended with the <kbd>message</kbd>, and appended with the remaining
     * number of tasks in the TaskList.
     *
     * @param message prologue message
     * @param task Task object to be printed
     */
    private void listTask(String message, Task task) {
        String prologue = TAB + message + BREAK;
        String content = TAB + HALF_TAB + task.toString() + BREAK;
        String epilogue = TAB + "Now you have " + tasks.getTotalTasksNumber() + " tasks in the list." + BREAK;
        printBetwBorders(prologue + content + epilogue);
    }

    /**
     * Prints the list of tasks in the TaskList.
     *
     * @param s prologue message
     * @param tasks
     */
    private void listTasks(String s, TaskList tasks) {
        String desc = TAB + s;
        printBetwBorders(desc + BREAK + tasks.tasksToString());
    }

    /**
     * Prints the task that is checked as done.
     *
     * @param s prologue message
     * @param doneTask Task object checked
     */
    private void listDoneTask(String s, Task doneTask) {
        String desc = TAB + s;
        printBetwBorders(desc + BREAK
                + TAB + HALF_TAB + doneTask + BREAK);
    }

    /**
     * Pretty prints the passed String in between horizontal borders.
     *
     * @param lineBlock String block to be formatted
     */
    public static void printBetwBorders(String lineBlock) {
        System.out.println(BORDER);
        System.out.print(lineBlock);
        System.out.println(BORDER);
    }
}
