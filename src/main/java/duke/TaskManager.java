package duke;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class TaskManager {
    private Task[] tasks;
    private final static int MAX_TASKS = 100;
    private final static int STARTING_TASK_NUMBER = 1;
    private final static String COMMAND_TODO = "todo";
    private final static String COMMAND_DEADLINE = "deadline";
    private final static String COMMAND_EVENT = "event";
    private final static String COMMAND_INVALID = "blah";

    private static int totalTasksNumber;
    private static int currentTaskNumber;

    public TaskManager() {
        tasks = new Task[MAX_TASKS];
        totalTasksNumber = 0;
        currentTaskNumber = STARTING_TASK_NUMBER;
    }

    public Task addTask(String userInputLine) throws InvalidDescriptionException, InvalidCommandException {
        String taskType;
        String taskDesc;

        try {
            taskType = getTypeFromInput(userInputLine);
            taskDesc = getDescFromInput(userInputLine);
        } catch (ArrayIndexOutOfBoundsException aie) {
            taskType = userInputLine.trim();
            throw new InvalidDescriptionException(taskType);
        } catch (InvalidCommandException ice) {
            throw new InvalidCommandException();
        }

        if (taskDesc.trim().isEmpty()) {
            throw new InvalidDescriptionException(taskType);
        }

        Task taskAdded;
        if (taskType.equals(COMMAND_DEADLINE)) {
            taskAdded = new Deadline(taskDesc, currentTaskNumber);
        } else if (taskType.equals(COMMAND_EVENT)) {
            taskAdded = new Event(taskDesc, currentTaskNumber);
        } else if (taskType.equals(COMMAND_TODO)) {
            taskAdded = new ToDo(taskDesc, currentTaskNumber);
        } else {
            taskAdded = new Task(taskDesc, currentTaskNumber);
        }
        tasks[totalTasksNumber] = taskAdded;

        totalTasksNumber++;
        currentTaskNumber++;

        return taskAdded;
    }

    private String getTypeFromInput(String userInputLine) {
        String taskType = "";
        if (taskHasType(userInputLine)) {
            taskType = splitInput(userInputLine)[0];
        }
        return taskType;
    }

    private String getDescFromInput(String userInputLine) throws InvalidDescriptionException, InvalidCommandException {
        String taskDesc = userInputLine;
        if (taskHasType(userInputLine)) {
            taskDesc = splitInput(userInputLine)[1];
        }

        if (isTaskType(taskDesc)) {
            throw new InvalidDescriptionException(taskDesc);
        } else if (taskDesc.equals(COMMAND_INVALID)) {
            throw new InvalidCommandException();
        }

        return taskDesc;
    }

    private String[] splitInput(String userInputLine) {
        return userInputLine.split(" ", 2);
    }

    private boolean taskHasType(String input) {
        boolean containsDeadline = input.contains(COMMAND_DEADLINE + " ");
        boolean containsEvent    = input.contains(COMMAND_EVENT + " ");
        boolean containsToDo     = input.contains(COMMAND_TODO + " ");
        return containsDeadline || containsEvent || containsToDo;
    }

    private boolean isTaskType(String taskDesc) {
        return taskDesc.equals(COMMAND_DEADLINE)
                || taskDesc.equals(COMMAND_EVENT)
                || taskDesc.equals(COMMAND_TODO);
    }

    public static int getTotalTasksNumber() {
        return totalTasksNumber;
    }

    public Task taskChecked(String userInputLine) {
        String digitString = userInputLine.substring("done ".length());
        int taskDoneNumber = Integer.parseInt(digitString);
        if (taskDoneNumber > 0) {
            tasks[taskDoneNumber - 1].setDone(true);
            return tasks[taskDoneNumber - 1];
        }
        return null;
    }

    public String tasksToString() {
        StringBuilder taskList = new StringBuilder();
        for (Task task : tasks) {
            if (task != null) {
                taskList.append("    ");
                taskList.append(task.getTaskNumber() + ".");
                taskList.append(task.toString());
                taskList.append(System.lineSeparator());
            }
        }
        return taskList.toString();
    }
}