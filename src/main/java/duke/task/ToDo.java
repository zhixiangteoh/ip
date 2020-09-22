package duke.task;

import static duke.Parser.SYMBOL_TODO;

/**
 * ToDo task object. Inherits <kbd>Task</kbd> class.
 *
 * @see duke.task.Task
 */
public class ToDo extends Task {
    private String typeBox;

    /**
     * Constructor. Creates ToDo from given task description. Additionally has a type symbol.
     *
     * @param taskDesc
     */
    public ToDo(String taskDesc) {
        super(taskDesc);
        typeBox = "[" + SYMBOL_TODO + "]";
    }

    /**
     * Returns string file write-save representation of the ToDo object.
     *
     * @return write-save String format
     */
    public String getFileRepresentation() {
        // T | 0 | eat
        int isDoneBit = isDone() ? 1 : 0;
        return SYMBOL_TODO + FILE_SEPARATOR_TOKEN + isDoneBit + FILE_SEPARATOR_TOKEN + getTaskDesc();
    }

    /**
     * Returns command line output format.
     *
     * @return system output format
     */
    @Override
    public String toString() {
        // [T][x] eat
        return typeBox + getCheckBox() + " " + getTaskDesc();
    }
}
