public class Task {
    private static final String TICK = "[✓]";
    private static final String CROSS = "[✗]";

    private String taskDesc;
    private int taskNumber;
    private boolean isDone;
    private String checkBox;

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

    public String getCheckBox() {
        return (isDone) ? TICK : CROSS;
    }

    public String toString() {
        checkBox = (isDone) ? TICK : CROSS;
        return checkBox + " " + taskDesc;
    }
}
