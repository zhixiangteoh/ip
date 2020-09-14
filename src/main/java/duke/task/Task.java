package duke.task;

public class Task {
    private static final String TICK = "[✓]";
    private static final String CROSS = "[✗]";

    private String taskDesc;
    private boolean isDone;
    private String checkBox;

    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.isDone = false;
    }

    public String getTaskDesc() {
        return taskDesc;
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
