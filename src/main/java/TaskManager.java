import java.util.function.ToDoubleBiFunction;

public class TaskManager {
    private Task[] tasks;
    private final static int MAX_TASKS = 100;
    private final static int STARTING_TASK_NUMBER = 1;

    private static int totalTasksNumber;

    public TaskManager() {
        tasks = new Task[MAX_TASKS];
        totalTasksNumber = STARTING_TASK_NUMBER;
    }

    public void addTask(String userInputLine) {
        String taskType = getTaskType(userInputLine);

        if (taskType.equals("todo")) {
            tasks[totalTasksNumber] = new ToDo(userInputLine, totalTasksNumber);
        } else if (taskType.equals("deadline")) {
            tasks[totalTasksNumber] = new Deadline(userInputLine, totalTasksNumber);
        } else if (taskType.equals("event")) {
            tasks[totalTasksNumber] = new Event(userInputLine, totalTasksNumber);
        } else {
            System.out.println("Invalid Task type!");
        }

        totalTasksNumber++;
    }

    private String getTaskType(String userInputLine) {
        int spaceIndex = userInputLine.indexOf(" ");
        // returns first word in userInputLine
        return userInputLine.substring(0, spaceIndex);
    }

    public static int getTotalTasksNumber() {
        return totalTasksNumber;
    }

    public Task getTask(int taskNumber) {
        return tasks[taskNumber];
    }

    public int checkTask(String userInputLine) {
        String digitString = userInputLine.substring("done ".length()); // 5 is where digit starts
        int taskDoneNumber = Integer.parseInt(digitString);
        if (taskDoneNumber > 0) {
            tasks[taskDoneNumber].setDone(true);
            return taskDoneNumber;
        }
        return -1;
    }

    public String tasksToString() {
        StringBuilder taskList = new StringBuilder();
        for (Task task : tasks) {
            if (task != null) {
                taskList.append("    ");
                taskList.append(task.toString());
                taskList.append(System.lineSeparator());
            }
        }
        return taskList.toString();
    }
}