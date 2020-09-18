package duke;

import duke.exception.InvalidCommandException;
import duke.exception.EmptyDescriptionException;
import duke.exception.ParseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Set;

import static duke.Ui.COMMAND_BYE;
import static duke.Ui.COMMAND_LIST;
import static duke.Ui.COMMAND_DONE;
import static duke.Ui.COMMAND_DELETE;
import static duke.Ui.COMMAND_FIND;
import static duke.Ui.COMMAND_TODO;
import static duke.Ui.COMMAND_DEADLINE;
import static duke.Ui.COMMAND_EVENT;

public class Parser {
    public static final String SYMBOL_TODO = "T";
    public static final String SYMBOL_EVENT = "E";
    public static final String SYMBOL_DEADLINE = "D";
    public static final Set<String> DESC_COMMANDS = Set.of(COMMAND_TODO, COMMAND_EVENT, COMMAND_DEADLINE, COMMAND_FIND);
    public static final Set<String> OTHER_COMMANDS = Set.of(COMMAND_BYE, COMMAND_LIST, COMMAND_DONE, COMMAND_DELETE);

    public String getCommand(String userInputLine) throws InvalidCommandException, EmptyDescriptionException {
        if (OTHER_COMMANDS.contains(userInputLine)) {
            return userInputLine;
        }
        if (DESC_COMMANDS.contains(userInputLine.trim())) {
            throw new EmptyDescriptionException(userInputLine.trim());
        }

        try {
            int commandEndIndex = userInputLine.indexOf(" ");
            String command = userInputLine.substring(0, commandEndIndex);
            return command;
        } catch (Exception e) {
            throw new InvalidCommandException();
        }
    }

    public Task createTask(String taskLine) throws ParseException {
        // e.g., taskLine: T | 1 | read book
        String[] components = taskLine.split("\\|", 3);
        Task task;

        String typeSymbol = components[0].trim();
        boolean isDone = Boolean.parseBoolean(components[1].trim());
        String description = components[2].trim();

        switch (typeSymbol) {
        case SYMBOL_TODO:
            task = new ToDo(description);
            break;
        case SYMBOL_DEADLINE:
            String deadlineDesc = description.replace("|", "/by");
            task = new Deadline(deadlineDesc);
            break;
        case SYMBOL_EVENT:
            String eventDesc = description.replace("|", "/at");
            System.out.println(eventDesc);
            task = new Event(eventDesc);
            break;
        default:
            throw new ParseException("Unable to create Task object from file task input string.");
        }

        task.setDone(isDone);

        return task;
    }

    public String convertToFileInput(Task task) throws ParseException {
        String fileTaskLine = "";

        if (task instanceof ToDo) {
            ToDo todoTask = (ToDo) task;
            fileTaskLine = todoTask.getFileRepresentation();
        } else if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            fileTaskLine = deadlineTask.getFileRepresentation();
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            fileTaskLine = eventTask.getFileRepresentation();
        } else {
            throw new ParseException("Unable to convert Task object to file task input string.");
        }

        return fileTaskLine;
    }
}
