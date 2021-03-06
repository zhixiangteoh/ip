package duke;

import duke.exception.ParseException;
import duke.exception.InvalidCommandException;
import duke.exception.TaskIndexNotSpecifiedException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static duke.Storage.FILE_PATH;
import static duke.Ui.BREAK;
import static duke.Ui.COMMAND_TODO;
import static duke.Ui.COMMAND_EVENT;
import static duke.Ui.COMMAND_DEADLINE;
import static duke.Ui.COMMAND_DONE;
import static duke.Ui.COMMAND_DELETE;

/**
 * TaskList stores Tasks in an ArrayList of Task objects, and handles any and all bookkeeping of Tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;
    private Parser parser;
    private final static int STARTING_TASK_NUMBER = 1;

    /**
     * Constructor. Initializes a new ArrayList and new Storage. Loads the TaskList from a saved file, if any.
     *
     * @throws FileNotFoundException
     */
    public TaskList() throws FileNotFoundException {
        tasks = new ArrayList<>();
        storage = new Storage();
        parser = new Parser();
        loadTaskList();
    }

    /**
     * Constructor. Creates a TaskList from an existing List of Tasks.
     *
     * @param tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = (ArrayList<Task>) tasks;
    }

    /**
     * Reads saved file data into TaskList. Catches a ParseException for errors encountered while reading data.
     *
     * @throws FileNotFoundException
     */
    public void loadTaskList() throws FileNotFoundException {
        File saveFile = storage.load();
        if (!saveFile.exists()) {
            return;
        }

        try {
            readFileIntoTaskList(saveFile);
        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
        }
    }

    /**
     * Reads the saved file raw Task data into the ArrayList of Tasks.
     *
     * @param file saved file with raw Task contents
     * @throws ParseException Exception corresponding to errors encountered while reading data
     * @throws FileNotFoundException
     */
    public void readFileIntoTaskList(File file) throws ParseException, FileNotFoundException {
        Scanner fileSc = new Scanner(file);
        while (fileSc.hasNextLine()) {
            String taskLine = fileSc.nextLine();
            Task task = parser.createTask(taskLine);
            addTask(task);
        }
    }

    /**
     * Writes all Tasks in the TaskList to the saved file. Catches a ParseException pertaining to errors encountered
     * while writing data.
     */
    public void updateTasksFile() {
        try {
            File writeFile = new File(FILE_PATH);
            if (!writeFile.exists()) {
                System.out.println("Creating new file 'duke.txt':");
                writeFile.createNewFile();
            }

            FileWriter writer = new FileWriter(writeFile);
            for (Task task : tasks) {
                try {
                    String writeLine = parser.convertToFileInput(task);
                    writer.write(writeLine + BREAK);
                } catch (ParseException pe) {
                    System.out.println(pe.getMessage());
                }
            }
            writer.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    /**
     * Adds a Task object to the ArrayList of Tasks. Each addition is immediately written to saved file.
     *
     * @param task Task to be added to list
     */
    public void addTask(Task task) {
        tasks.add(task);
        updateTasksFile();
    }

    /**
     * Creates and returns a specific Task object added to the TaskList. Each additiion is immediately
     * written to saved file.
     *
     * Depending on the task type passed in, a ToDo, Deadline, or Event object is created, then added to the TaskList.
     *
     * @param taskType type of Task, i.e., ToDo, Event, Deadline
     * @param userInputLine full input line entered by user
     * @return added Task object
     * @throws InvalidCommandException
     */
    public Task addTask(String taskType, String userInputLine) throws InvalidCommandException {
        String taskDesc = getDescFromInput(userInputLine);

        Task taskAdded;
        switch (taskType) {
        case COMMAND_TODO:
            taskAdded = new ToDo(taskDesc);
            break;
        case COMMAND_DEADLINE:
            taskAdded = new Deadline(taskDesc);
            break;
        case COMMAND_EVENT:
            taskAdded = new Event(taskDesc);
            break;
        default:
            throw new InvalidCommandException();
        }

        tasks.add(taskAdded);
        updateTasksFile();

        return taskAdded;
    }

    /**
     * Finds all tasks that have task descriptions matching the search input for this command.
     *
     * @param userInputLine full user input line, containing the search term(s)
     * @return List of Tasks that match the search
     */
    public List<Task> find(String userInputLine) {
        String findDesc = getDescFromInput(userInputLine);

        List<Task> tasksFound = tasks.stream()
                .filter(task -> task.getTaskDesc().contains(findDesc))
                .collect(Collectors.toList());

        return tasksFound;
    }

    private String getDescFromInput(String userInputLine) {
        String taskDesc = "";
        // split into 2 parts, command and description, by spaces delimiter.
        String[] taskDetails = userInputLine.split("\\s+", 2);
        // description in second part
        taskDesc = taskDetails[1];

        return taskDesc;
    }

    /**
     * Returns Task object checked as done, or null if no Task object checked.
     *
     * Throws a TaskIndexNotSpecifiedException if task index not specified by user, or task index cannot be parsed
     * from the user input.
     *
     * @param userInputLine full user input line
     * @return Task object, or null
     * @throws TaskIndexNotSpecifiedException
     */
    public Task taskChecked(String userInputLine) throws TaskIndexNotSpecifiedException {
        try {
            String digitString = getDigitString(userInputLine, COMMAND_DONE);
            int taskDoneNumber = Integer.parseInt(digitString) - STARTING_TASK_NUMBER;
            // handle possibility of invalid index (< 0) entered
            if (taskDoneNumber >= 0) {
                tasks.get(taskDoneNumber).setDone(true);
                updateTasksFile();
                return tasks.get(taskDoneNumber);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new TaskIndexNotSpecifiedException();
        }
    }

    /**
     * Returns Task object deleted, or null if no Task object deleted.
     *
     * Throws a TaskIndexNotSpecifiedException if task index not specified by user, or task index cannot be parsed
     * from the user input.
     *
     * @param userInputLine full user input line
     * @return Task object, or null
     * @throws TaskIndexNotSpecifiedException
     */
    public Task taskDeleted(String userInputLine) throws TaskIndexNotSpecifiedException {
        try {
            String digitString = getDigitString(userInputLine, COMMAND_DELETE);
            int taskDeletedNumber = Integer.parseInt(digitString) - STARTING_TASK_NUMBER;
            // handle possibility of invalid index (< 0) entered
            if (taskDeletedNumber >= 0) {
                Task deletedTask = tasks.get(taskDeletedNumber);
                tasks.remove(taskDeletedNumber);
                updateTasksFile();
                return deletedTask;
            } else {
                return null;
            }
        } catch (Exception e){
            throw new TaskIndexNotSpecifiedException();
        }
    }

    private String getDigitString(String userInputLine, String command) {
        return userInputLine.substring((command + " ").length());
    }

    /**
     * Returns String representation of TaskList.
     *
     * @return formatted TaskList output
     */
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

    public String tasksFoundToString(TaskList mainTasks) {
        StringBuilder taskList = new StringBuilder();
        for (Task task : tasks) {
            if (task != null) {
                taskList.append("    ");
                taskList.append(mainTasks.getTasks().indexOf(task) + ".");
                taskList.append(task.toString());
                taskList.append(System.lineSeparator());
            }
        }
        return taskList.toString();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns total number of tasks in the TaskList.
     *
     * @return total number of tasks
     */
    public int getTotalTasksNumber() {
        return tasks.size();
    }
}