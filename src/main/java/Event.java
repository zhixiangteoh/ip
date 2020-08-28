public class Event extends Task {
    private String typeBox;

    public Event(String taskDesc, int totalTasksNumber) {
        super(taskDesc, totalTasksNumber);
        typeBox = "[E]";
    }

    @Override
    public String getTaskDesc() {
        String[] splitDesc = super.getTaskDesc().split("/at", 2);
        String desc = splitDesc[0];
        String time = splitDesc[1];
        return desc + "(at:" + time + ")";
    }

    @Override
    public String toString() {
        String checkBox = (isDone()) ? "[✓]" : "[✗]";
        // [E][x] eat (at: 10am)
        return typeBox + checkBox + " " + getTaskDesc();
    }
}
