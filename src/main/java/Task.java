public class Task {
    private String taskDesc;
    private int taskNumber;

    private static int totalTasksNumber;

    public Task() {
       this("");
    }

    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.taskNumber = totalTasksNumber++;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public static void setTotalTasksNumber(int totalTasksNumber) {
        Task.totalTasksNumber = totalTasksNumber;
    }

    public static int getTotalTasksNumber() {
        return totalTasksNumber;
    }

    public String toString() {
        return (taskNumber+1) + ". " + taskDesc;
    }
}
