package report;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Alexey Ushakov
 */
public class ReportMaker {
    private static ReportMaker instance = new ReportMaker();
    private List<String> taskList;

    private ReportMaker() {
        taskList = new LinkedList<>();
    }

    public static ReportMaker getInstance() {
        return instance;
    }

    public List<String> getTaskList() {
        return taskList;
    }

    public void clear() {
        taskList.clear();
    }

    public void addTask(String task) {
        taskList.add(task);
    }

    public int taskCount() {
        return taskList.size();
    }
}
