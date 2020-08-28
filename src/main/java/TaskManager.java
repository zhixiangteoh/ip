public class TaskManager {
    private Task[] tasks;
    private final static int MAX_TASKS = 100;
    private final static int STARTING_TASK_NUMBER = 1;

    private static int totalTasksNumber;
    private static int currentTaskNumber;

    public TaskManager() {
        tasks = new Task[MAX_TASKS];
        totalTasksNumber = 0;
        currentTaskNumber = STARTING_TASK_NUMBER;
    }

    public Task addTask(String userInputLine) {
        String taskType = "";
        String taskDesc = userInputLine;
        if (userInputLine.contains(" ")) {
            String[] splitInput = userInputLine.split(" ", 2);
            taskType = splitInput[0];
            taskDesc = splitInput[1];
        }

        Task taskAdded;
        if (taskType.equals("deadline")) {
            taskAdded = new Deadline(taskDesc, currentTaskNumber);
        } else if (taskType.equals("event")) {
            taskAdded = new Event(taskDesc, currentTaskNumber);
        } else if (taskType.equals("todo")) {
            taskAdded = new ToDo(taskDesc, currentTaskNumber);
        } else {
            taskAdded = new Task(taskDesc, currentTaskNumber);
        }
        tasks[totalTasksNumber] = taskAdded;

        totalTasksNumber++;
        currentTaskNumber++;

        return taskAdded;
    }

    public static int getTotalTasksNumber() {
        return totalTasksNumber;
    }

    public int checkTask(String userInputLine) {
        String digitString = userInputLine.substring("done ".length()); // 5 is where digit starts
        int taskDoneNumber = Integer.parseInt(digitString);
        if (taskDoneNumber > 0) {
            tasks[taskDoneNumber - 1].setDone(true);
            return taskDoneNumber;
        }
        return -1;
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