package duke;

import duke.exception.ParseException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static duke.Duke.BREAK;
import static duke.Duke.FILE_PATH;

public class TaskManager {
    private static ArrayList<Task> tasks;
    private static Parser parser = new Parser();
    private final static int STARTING_TASK_NUMBER = 1;
    private final static String COMMAND_TODO = "todo";
    private final static String COMMAND_DEADLINE = "deadline";
    private final static String COMMAND_EVENT = "event";
    private final static String COMMAND_DONE = "done";
    private final static String COMMAND_DELETE = "delete";
    private final static String COMMAND_INVALID = "blah";

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void readFile(File file) throws ParseException, FileNotFoundException {
        Scanner fileSc = new Scanner(file);
        while (fileSc.hasNextLine()) {
            String taskLine = fileSc.nextLine();
            Task task = parser.createTask(taskLine);
            addTask(task);
        }
    }

    public static void updateTasksFile() {
        try {
            File writeFile = new File(FILE_PATH);
            if (!writeFile.exists()) {
                writeFile.createNewFile();
            }

            FileWriter writer = new FileWriter(writeFile);
            for (Task task : tasks) {
                String writeLine = parser.convertToFileInput(task);
                writer.write(writeLine + BREAK);
            }
            writer.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        updateTasksFile();
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
            taskAdded = new Deadline(taskDesc);
        } else if (taskType.equals(COMMAND_EVENT)) {
            taskAdded = new Event(taskDesc);
        } else if (taskType.equals(COMMAND_TODO)) {
            taskAdded = new ToDo(taskDesc);
        } else {
            taskAdded = new Task(taskDesc);
        }
        tasks.add(taskAdded);
        updateTasksFile();

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
        return tasks.size();
    }

    // returns Task object that is checked as done.
    // returns null if no Task object checked.
    public Task taskChecked(String userInputLine) {
        String digitString = getDigitString(userInputLine, COMMAND_DONE);
        int taskDoneNumber = Integer.parseInt(digitString) - STARTING_TASK_NUMBER;
        if (taskDoneNumber >= 0) {
            tasks.get(taskDoneNumber).setDone(true);
            updateTasksFile();
            return tasks.get(taskDoneNumber);
        }
        return null;
    }

    public Task taskDeleted(String userInputLine) {
        String digitString = getDigitString(userInputLine, COMMAND_DELETE);
        int taskDeletedNumber = Integer.parseInt(digitString) - STARTING_TASK_NUMBER;
        if (taskDeletedNumber >= 0) {
            Task deletedTask = tasks.get(taskDeletedNumber);
            tasks.remove(taskDeletedNumber);
            updateTasksFile();
            return deletedTask;
        }
        return null;
    }

    private String getDigitString(String userInputLine, String command) {
        return userInputLine.substring((command + " ").length());
    }

    public String tasksToString() {
        StringBuilder taskList = new StringBuilder();
        int currentTaskNumber = STARTING_TASK_NUMBER;
        for (Task task : tasks) {
            if (task != null) {
                taskList.append("    ");
                taskList.append(currentTaskNumber++ + ".");
                taskList.append(task.toString());
                taskList.append(System.lineSeparator());
            }
        }
        return taskList.toString();
    }
}