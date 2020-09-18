package duke.exception;

public class ParseException extends DukeException {
    private String message;

    public ParseException() {
        super();
    }

    public ParseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
