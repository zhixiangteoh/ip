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
import static duke.Ui.COMMAND_TODO;
import static duke.Ui.COMMAND_DEADLINE;
import static duke.Ui.COMMAND_EVENT;

/**
 * Parser handling most parsing operations, particularly pertaining to parsing the command from user input, reading
 * and writing saved file data, and date-time parsing.
 */
public class Parser {
    public static final String SYMBOL_TODO = "T";
    public static final String SYMBOL_EVENT = "E";
    public static final String SYMBOL_DEADLINE = "D";
    public static final Set<String> TASK_COMMANDS = Set.of(COMMAND_TODO, COMMAND_EVENT, COMMAND_DEADLINE);

    /**
     * Returns user's command for the given input line, and handles some erroneous input.
     *
     * @param userInputLine full user input line
     * @return user command as a String
     * @throws InvalidCommandException
     * @throws EmptyDescriptionException
     */
    public String getCommand(String userInputLine) throws InvalidCommandException, EmptyDescriptionException {
        // these input lines consists only of the commands, so just return them and catch bespoke errors
        if (userInputLine.equals(COMMAND_BYE) || userInputLine.equals(COMMAND_LIST)) {
            return userInputLine;
        }
        if (userInputLine.equals(COMMAND_DONE) || userInputLine.equals(COMMAND_DELETE)) {
            return userInputLine;
        }
        // these input lines contain the commands which cannot have empty descriptions
        if (TASK_COMMANDS.contains(userInputLine)) {
            throw new EmptyDescriptionException(userInputLine);
        }

        try {
            int commandEndIndex = userInputLine.indexOf(" ");
            String command = userInputLine.substring(0, commandEndIndex);
            return command;
        } catch (Exception e) {
            // catches all inputs that don't have at least two words and are not in OTHER_COMMANDS
            throw new InvalidCommandException();
        }
    }

    /**
     * Returns the created Task object from the corresponding saved file raw input.
     *
     * @param taskLine saved file Task line
     * @return created Task
     * @throws ParseException
     */
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

    /**
     * Returns save file input format for the given Task. Essentially the inverse of createTask.
     *
     * @param task Task to be converted to save file format
     * @return save file String format
     * @throws ParseException
     */
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
