public class Task {
    private String taskDesc;
    private int taskNumber;
    private boolean isDone;

    public Task() {
        this("", 0);
    }

    public Task(String taskDesc, int taskNumber) {
        this.taskDesc = taskDesc;
        this.taskNumber = taskNumber;
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

    public String toString() {
        String checkBox = (isDone) ? "[✓]" : "[✗]";
        return checkBox + " " + taskDesc;
    }
}
