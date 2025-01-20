package history;

import task.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private final Map <Integer,Node <Task>> taskHistory = new LinkedHashMap<>();

    @Override
    public void linkLast(Task task) {
        if (0 == taskHistory.size()) {
            taskHistory.put(task.getId(), new Node<>(task));
        } else {
            if (taskHistory.containsKey(task.getId())){
                remove(task.getId());
            }
            Node<Task> newTask = new Node<>(task);
            newTask.prev = taskHistory.get(taskHistory.size());
            taskHistory.put(task.getId(), newTask);
            Node<Task> oldTask = taskHistory.get(taskHistory.size() - 1);
            oldTask.next = taskHistory.get(taskHistory.size());
            taskHistory.put(taskHistory.size()-1,oldTask);
        }
    }

    @Override
    public List <Task> getTasks (){
        List <Task> list = new LinkedList<>();
        for (Node <Task> t : taskHistory.values()){
            list.add(t.data);
        }
        return list;
    }

    @Override
    public void remove (int id){
        if (taskHistory.containsKey(id)){
            removeNode(taskHistory.get(id));
            taskHistory.remove(id);
        }
    }

    private void removeNode (Node <Task> taskNode){
            Node <Task> prevTask = taskNode.prev;
            Node <Task> nextTask = taskNode.next;
            prevTask.next = taskNode.next;
            nextTask.prev = taskNode.prev;
            taskHistory.put(prevTask.data.getId(),prevTask);
            taskHistory.put(nextTask.data.getId(),nextTask);
    }
}