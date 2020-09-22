package duke.task;

/**
 * Task object. Parent class of all specific Task objects, namely ToDo, Deadline, and Event.
 */
public class Task {
    private static final String TICK = "[✓]";
    private static final String CROSS = "[✗]";
    static final String FILE_SEPARATOR_TOKEN = " | ";

    private String taskDesc;
    private boolean isDone;
    private String checkBox;

    /**
     * Constructor. Builds a Task object from a given task description.
     *
     * Task initialized as undone.
     *
     * @param taskDesc
     */
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
