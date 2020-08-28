public class ToDo extends Task {
    private String typeBox;

    public ToDo(String taskDesc, int totalTasksNumber) {
        super(taskDesc, totalTasksNumber);
        typeBox = "[T]";
    }

    @Override
    public String toString() {
        String checkBox = (isDone()) ? "[✓]" : "[✗]";
        // [T][x] eat
        return typeBox + checkBox + " " + getTaskDesc();
    }
}
