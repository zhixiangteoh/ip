package duke;

import duke.exception.InvalidCommandException;
import duke.exception.EmptyDescriptionException;
import duke.exception.TaskIndexNotSpecifiedException;
import duke.task.Task;

import java.util.Scanner;

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

    private TaskList tasks;
    private static Parser parser;

    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    public void showLogo() {
        String logo = " ____            _\n"
                + "|  _ \\ _   _   _| | ___\n"
                + "| | | | | | |/ _  |/ _ \\\n"
                + "| |_| | |_| | |_| |  __/\n"
                + "|____/ \\__,_|\\____|\\___|";
        System.out.println("Hello from\n" + logo);
        System.out.println(BORDER);
    }

    public void greet() {
        String greeting = TAB + "Hello! I'm Dude" + BREAK
                + TAB + "What can I do for you?";
        System.out.println(greeting);
        System.out.println(BORDER);
    }

    public void interact(TaskList tasks) {
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
                listTasks("Here are the tasks in your list:");
                break;
            case COMMAND_DONE:
                // returns task that is checked if a task is successfully checked, else returns null
                try {
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

    public void sayBye() {
        String sayBye = TAB + "Bye. Hope to see you again soon!" + BREAK;
        printBetwBorders(sayBye);
    }

    private void showTaskIndexNotSpecifiedMessage() {
        String message = TAB + "☹ OOPS!!! I won't know which task unless you specify an index!" + BREAK;
        printBetwBorders(message);
    }

    private void showInvalidCommandMessage() {
        String message = TAB + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + BREAK;
        printBetwBorders(message);
    }

    private void showEmptyDescMessage(EmptyDescriptionException ide) {
        String message = TAB + "☹ OOPS!!! The description of a(n) "
                + ide.getTaskType() + " cannot be empty." + BREAK;
        printBetwBorders(message);
    }

    private void listTask(String message, Task task) {
        String prologue = TAB + message + BREAK;
        String content = TAB + HALF_TAB + task.toString() + BREAK;
        String epilogue = TAB + "Now you have " + tasks.getTotalTasksNumber() + " tasks in the list." + BREAK;
        printBetwBorders(prologue + content + epilogue);
    }

    private boolean taskIsChecked(Task doneTask) {
        return doneTask != null;
    }

    private boolean taskIsDeleted(Task deletedTask) {
        return deletedTask != null;
    }

    private void listTasks(String s) {
        String desc = TAB + s;
        printBetwBorders(desc + BREAK + tasks.tasksToString());
    }

    private void listDoneTask(String s, Task doneTask) {
        String desc = TAB + s;
        printBetwBorders(desc + BREAK
                + TAB + HALF_TAB + doneTask + BREAK);
    }

    private boolean commandIsDone(String userInputLine) {
        return userInputLine.matches("done ([0-9]+)");
    }

    private boolean commandIsDelete(String userInputLine) {
        return userInputLine.matches("delete ([0-9]+)");
    }

    public static void printBetwBorders(String lineBlock) {
        System.out.println(BORDER);
        System.out.print(lineBlock);
        System.out.println(BORDER);
    }
}
