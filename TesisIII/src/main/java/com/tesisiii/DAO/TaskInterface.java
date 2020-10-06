
package com.tesisiii.DAO;


import com.tesisiii.model.Task;
import java.util.List;

public interface TaskInterface {
    public void addTask(Task t);
    public void removeTask(Task t);
    public Task getTask(Integer id);
    public void editTask(Task t);
    public List<Task> listTasks();
    public List<Task> UserProjectListTasks(Integer pId,int uId);
    public List<Task> listTasksByProyect(Integer ProjectId);
    public void mergeTask(Task t);
    public List<Task> ListTasksByAUser( int uId);
}
