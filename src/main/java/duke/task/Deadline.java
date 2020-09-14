package duke.task;

public class Deadline extends Task {
    private String typeBox;

    public Deadline(String taskDesc) {
        super(taskDesc);
        typeBox = "[D]";
    }

    @Override
    public String getTaskDesc() {
        String[] splitDesc = super.getTaskDesc().split("/by", 2);
        String desc = splitDesc[0];
        String time = splitDesc[1];
        return desc + "(by:" + time + ")";
    }

    @Override
    public String toString() {
        // [D][x] eat (by: 10am)
        return typeBox + getCheckBox() + " " + getTaskDesc();
    }
}
