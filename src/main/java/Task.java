public class Task {
    private String taskDesc;
    private int taskNumber;

    private static int totalTasksNumber;

    public Task() {
       this("", 0);
    }

    public Task(String taskDesc, int taskNumber) {
        this.taskDesc = taskDesc;
        this.taskNumber = taskNumber;

        totalTasksNumber++;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String toString() {
        return (taskNumber+1) + ". " + taskDesc;
    }

    public static void setTotalTasksNumber(int totalTasksNumber) {
        Task.totalTasksNumber = totalTasksNumber;
    }

    public static int getTotalTasksNumber() {
        return totalTasksNumber;
    }
}
