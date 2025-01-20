package history;

import task.Task;

import java.util.List;

public interface HistoryManager {

    void linkLast (Task task);

    List getTasks ();

    void remove(int id);
}

