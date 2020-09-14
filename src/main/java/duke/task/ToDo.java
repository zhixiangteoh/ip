package duke.task;

import static duke.Parser.SYMBOL_TODO;

public class ToDo extends Task {
    private String typeBox;

    public ToDo(String taskDesc) {
        super(taskDesc);
        typeBox = "[" + SYMBOL_TODO + "]";
    }

    public String getFileRepresentation() {
        // T | 0 | eat
        int isDoneBit = isDone() ? 1 : 0;
        return SYMBOL_TODO + " | " + isDoneBit + " | " + getTaskDesc();
    }

    @Override
    public String toString() {
        // [T][x] eat
        return typeBox + getCheckBox() + " " + getTaskDesc();
    }
}
