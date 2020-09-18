package duke.exception;

public class EmptyDescriptionException extends DukeException {
    private String taskType;

    public EmptyDescriptionException(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskType() {
        return taskType;
    }
}
