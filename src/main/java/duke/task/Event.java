package duke.task;

public class Event extends Task {
    private String typeBox;

    public Event(String taskDesc) {
        super(taskDesc);
        typeBox = "[E]";
    }

    @Override
    public String getTaskDesc() {
        String[] splitDesc = super.getTaskDesc().split("/at", 2);
        String desc = splitDesc[0];
        String time = splitDesc[1];
        return desc + "(at:" + time + ")";
    }

    @Override
    public String toString() {
        // [E][x] eat (at: 10am)
        return typeBox + getCheckBox() + " " + getTaskDesc();
    }
}
