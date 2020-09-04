package duke.task;

public class ToDo extends Task {
    private String typeBox;

    public ToDo(String taskDesc, int totalTasksNumber) {
        super(taskDesc, totalTasksNumber);
        typeBox = "[T]";
    }

    @Override
    public String toString() {
        // [T][x] eat
        return typeBox + getCheckBox() + " " + getTaskDesc();
    }
}
