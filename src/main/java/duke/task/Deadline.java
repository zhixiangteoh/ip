package duke.task;

import duke.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private LocalDateTime datetime;
    private String datetimeString;

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
        String dateTimeString = splitDesc[1].trim();
        Parser parser = new Parser();
        parseDate(parser, dateTimeString);

        typeBox = "[" + SYMBOL_DEADLINE + "]";
    }

    private void parseDate(Parser parser, String dateTimeString) {
        try {
            datetime = parser.getDateTime(dateTimeString);
            datetimeString = toDateTimeString(datetime);
        } catch (Exception e) {
            datetimeString = dateTimeString;
        }
    }

    private String toDateTimeString(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }

    /**
     * Returns a formatted task description, with both text description and date-time output.
     *
     * @return
     */
    @Override
    public String getTaskDesc() {
        return bareDesc + " (by: " + datetimeString + ")";
    }

    /**
     * Returns string file write-save representation of the ToDo object.
     *
     * @return write-save String format
     */
    public String getFileRepresentation() {
        // D | 0 | eat | 10am
        int isDoneBit = isDone() ? 1 : 0;
        return SYMBOL_DEADLINE + FILE_SEPARATOR_TOKEN + isDoneBit + FILE_SEPARATOR_TOKEN + bareDesc + FILE_SEPARATOR_TOKEN + datetimeString;
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
