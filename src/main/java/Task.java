public class Task {
    private String taskDesc;
    private int taskNumber;

    public Task() {
       this("", 0);
    }

    public Task(String taskDesc, int taskNumber) {
        this.taskDesc = taskDesc;
        this.taskNumber = taskNumber;
    }


    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String toString() {
        return taskNumber + ". " + taskDesc;
    }
}
