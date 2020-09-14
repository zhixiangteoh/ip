package duke.task;

import static duke.Parser.SYMBOL_EVENT;

public class Event extends Task {
    private String typeBox;
    private String bareDesc;
    private String time;

    public Event(String taskDesc) {
        super(taskDesc);
        String[] splitDesc = super.getTaskDesc().split("/at", 2);
        bareDesc = splitDesc[0].trim();
        time = splitDesc[1].trim();
        typeBox = "[" + SYMBOL_EVENT + "]";
    }

    @Override
    public String getTaskDesc() {
        return bareDesc + " (at: " + time + ")";
    }

    public String getFileRepresentation() {
        // E | 0 | eat | 10am
        int isDoneBit = isDone() ? 1 : 0;
        return SYMBOL_EVENT + " | " + isDoneBit + " | " + bareDesc + " | " + time;
    }

    @Override
    public String toString() {
        // [E][x] eat (at: 10am)
        return typeBox + getCheckBox() + " " + getTaskDesc();
    }
}
