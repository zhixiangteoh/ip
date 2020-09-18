package duke.task;

import static duke.Parser.SYMBOL_DEADLINE;

/**
 * Deadline task object. Inherits <kbd>Task</kbd> class.
 *
 * Deadline task requires the user entered input to also have a "/by" delimiter, after which the remaining String of
 * characters are perceived as being a representation of date and time.
 *
 * @see duke.task.Task
 */
public class Deadline extends Task {
    private String typeBox;
    private String bareDesc;
    private String time;

    /**
     * Constructor. Creates Deadline from given task description. Additionally has a type symbol.
     *
     * Splits task description into two: text description and date-time object, and saves this information.
     *
     * @param taskDesc
     */
    public Deadline(String taskDesc) {
        super(taskDesc);
        String[] splitDesc = super.getTaskDesc().split("/by", 2);
        bareDesc = splitDesc[0].trim();
        time = splitDesc[1].trim();
        typeBox = "[" + SYMBOL_DEADLINE + "]";
    }

    /**
     * Returns a formatted task description, with both text description and date-time output.
     *
     * @return
     */
    @Override
    public String getTaskDesc() {
        return bareDesc + " (by: " + time + ")";
    }

    /**
     * Returns string file write-save representation of the ToDo object.
     *
     * @return write-save String format
     */
    public String getFileRepresentation() {
        // D | 0 | eat | 10am
        int isDoneBit = isDone() ? 1 : 0;
        return SYMBOL_DEADLINE + " | " + isDoneBit + " | " + bareDesc + " | " + time;
    }

    /**
     * Returns command line output format.
     *
     * @return system output format
     */
    @Override
    public String toString() {
        // [D][x] eat (by: 10am)
        return typeBox + getCheckBox() + " " + getTaskDesc();
    }
}
