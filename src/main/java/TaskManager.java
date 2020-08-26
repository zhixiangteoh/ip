public class TaskManager {
    private Task[] tasks;
    private static final int MAX_TASKS = 100;

    public TaskManager() {
        tasks = new Task[MAX_TASKS];
        // implementation decision: taskNumber starts from 1, in line with printed task list
        Task.setTotalTasksNumber(1);
    }

    public void addTask(String userInputLine) {
        int taskNumber = Task.getTotalTasksNumber();
        tasks[taskNumber] = new Task(userInputLine);
    }

    public int checkedTask(String userInputLine) {
        String digitString = userInputLine.substring(5); // 5 is where digit starts
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