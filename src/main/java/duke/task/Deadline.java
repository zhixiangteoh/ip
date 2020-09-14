package duke.task;

import static duke.Parser.SYMBOL_DEADLINE;

public class Deadline extends Task {
    private String typeBox;
    private String bareDesc;
    private String time;

    public Deadline(String taskDesc) {
        super(taskDesc);
        String[] splitDesc = super.getTaskDesc().split("/by", 2);
        bareDesc = splitDesc[0].trim();
        time = splitDesc[1].trim();
        typeBox = "[" + SYMBOL_DEADLINE + "]";
    }

    @Override
    public String getTaskDesc() {
        return bareDesc + " (by: " + time + ")";
    }

    public String getFileRepresentation() {
        // D | 0 | eat | 10am
        int isDoneBit = isDone() ? 1 : 0;
        return SYMBOL_DEADLINE + " | " + isDoneBit + " | " + bareDesc + " | " + time;
    }

    @Override
    public String toString() {
        // [D][x] eat (by: 10am)
        return typeBox + getCheckBox() + " " + getTaskDesc();
    }
}
