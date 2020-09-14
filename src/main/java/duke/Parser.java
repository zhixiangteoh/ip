package duke;

import duke.exception.ParseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Parser {
    public static final String SYMBOL_TODO = "T";
    public static final String SYMBOL_EVENT = "E";
    public static final String SYMBOL_DEADLINE = "D";

    public static Task createTask(String taskLine) throws ParseException {
        // e.g., taskLine: T | 1 | read book
        String[] components = taskLine.split("|", 3);
        Task task;

        String typeSymbol = components[0];
        boolean isDone = Boolean.parseBoolean(components[1]);
        String description = components[2];

        switch (typeSymbol) {
        case SYMBOL_TODO:
            task = new ToDo(description);
            break;
        case SYMBOL_DEADLINE:
            description.replace("|", "/by");
            task = new Deadline(description);
            break;
        case SYMBOL_EVENT:
            description.replace("|", "/at");
            task = new Event(description);
            break;
        default:
            throw new ParseException("Unable to create Task object from file task input string.");
        }

        task.setDone(isDone);

        return task;
    }

    public String convertToFileInput(Task task) throws ParseException {
        String fileTaskLine = "";
        int isDoneBit = task.isDone() ? 1 : 0;
        if (task instanceof ToDo) {
            ToDo todoTask = (ToDo) task;
            fileTaskLine += SYMBOL_TODO + " | " + isDoneBit + " | " + todoTask.getTaskDesc();
        } else if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            String[] splitDesc = splitDescription(deadlineTask);
            String description = splitDesc[0].trim();
            String time = splitDesc[1].trim();
            fileTaskLine += SYMBOL_DEADLINE + " | " + isDoneBit + " | " + description + " | " + time;
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            String[] splitDesc = splitDescription(eventTask);
            String description = splitDesc[0].trim();
            String time = splitDesc[1].trim();
            fileTaskLine += SYMBOL_EVENT + " | " + isDoneBit + " | " + description + " | " + time;
        } else {
            throw new ParseException("Unable to convert Task object to file task input string.");
        }

        return fileTaskLine;
    }

    private static String[] splitDescription(Deadline deadlineTask) {
        return deadlineTask.getTaskDesc().replace("(", "").replace(")", "").split("by:", 2);
    }

    private static String[] splitDescription(Event eventTask) {
        return eventTask.getTaskDesc().replace("(", "").replace(")", "").split("at:", 2);
    }
}
