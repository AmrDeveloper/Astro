package astro.model;

public class Task {

    private int startIndex;
    private int endIndex;
    private String taskBody;

    private static final String TASK_FORMAT = "(%d, %d)%s";

    public Task(int startIndex, int endIndex, String taskBody) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.taskBody = taskBody;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public String getTaskBody() {
        return taskBody;
    }

    @Override
    public String toString() {
        return String.format(TASK_FORMAT,startIndex,endIndex,taskBody);
    }
}
