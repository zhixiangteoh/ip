public class Task {
    private String taskDesc;
    private int taskNumber;
    private boolean isDone;

    private static int totalTasksNumber;

    public Task() {
       this("");
    }

    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.taskNumber = totalTasksNumber++;
        this.isDone = false;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public static void setTotalTasksNumber(int totalTasksNumber) {
        Task.totalTasksNumber = totalTasksNumber;
    }

    public static int getTotalTasksNumber() {
        return totalTasksNumber;
    }

    public String toString() {
        String checkBox = (isDone) ? "[✓]" : "[✗]";
        return taskNumber + ". " + checkBox + " " + taskDesc;
    }
}
