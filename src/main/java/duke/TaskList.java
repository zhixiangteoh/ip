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

import static duke.Ui.BREAK;
import static duke.Storage.FILE_PATH;
import static duke.Ui.COMMAND_TODO;
import static duke.Ui.COMMAND_EVENT;
import static duke.Ui.COMMAND_DEADLINE;
import static duke.Ui.COMMAND_DONE;
import static duke.Ui.COMMAND_DELETE;

public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;
    private static Parser parser = new Parser();
    private final static int STARTING_TASK_NUMBER = 1;

    public TaskList() throws FileNotFoundException {
        tasks = new ArrayList<>();
        storage = new Storage();
        loadTaskList();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = (ArrayList<Task>) tasks;
    }

    public void loadTaskList() throws FileNotFoundException {
        try {
            readFileIntoTaskList(storage.load());
        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
        }
    }

    public void readFileIntoTaskList(File file) throws ParseException, FileNotFoundException {
        Scanner fileSc = new Scanner(file);
        while (fileSc.hasNextLine()) {
            String taskLine = fileSc.nextLine();
            Task task = parser.createTask(taskLine);
            addTask(task);
        }
    }

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

    public void addTask(Task task) {
        tasks.add(task);
        updateTasksFile();
    }

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

    public List<Task> find(String userInputLine) {
        String findDesc = getDescFromInput(userInputLine);

        List<Task> tasksFound = tasks.stream()
                .filter(task -> task.getTaskDesc().contains(findDesc))
                .collect(Collectors.toList());

        return tasksFound;
    }

    private String getDescFromInput(String userInputLine) {
        String taskDesc = "";
        String[] taskDetails = userInputLine.split(" ", 2);
        taskDesc = taskDetails[1];

        return taskDesc;
    }

    // returns Task object that is checked as done.
    // returns null if no Task object checked.
    public Task taskChecked(String userInputLine) throws TaskIndexNotSpecifiedException {
        try {
            String digitString = getDigitString(userInputLine, COMMAND_DONE);
            int taskDoneNumber = Integer.parseInt(digitString) - STARTING_TASK_NUMBER;
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

    // returns Task object that is deleted.
    // returns null if no Task object deleted.
    public Task taskDeleted(String userInputLine) throws TaskIndexNotSpecifiedException {
        try {
            String digitString = getDigitString(userInputLine, COMMAND_DELETE);
            int taskDeletedNumber = Integer.parseInt(digitString) - STARTING_TASK_NUMBER;
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

    public int getTotalTasksNumber() {
        return tasks.size();
    }
}