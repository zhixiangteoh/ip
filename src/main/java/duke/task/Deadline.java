package duke.task;

import duke.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static duke.Parser.SYMBOL_DEADLINE;

public class Deadline extends Task {
    private String typeBox;
    private String bareDesc;
    private LocalDateTime datetime;
    private String datetimeString;

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

    @Override
    public String getTaskDesc() {
        return bareDesc + " (by: " + datetimeString + ")";
    }

    public String getFileRepresentation() {
        // D | 0 | eat | 10am
        int isDoneBit = isDone() ? 1 : 0;
        return SYMBOL_DEADLINE + " | " + isDoneBit + " | " + bareDesc + " | " + datetimeString;
    }

    @Override
    public String toString() {
        // [D][x] eat (by: 10am)
        return typeBox + getCheckBox() + " " + getTaskDesc();
    }
}
