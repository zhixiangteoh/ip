package duke.exception;

public class InvalidDescriptionException extends DukeException {
    private String taskType;

    public InvalidDescriptionException(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskType() {
        return taskType;
    }
}
