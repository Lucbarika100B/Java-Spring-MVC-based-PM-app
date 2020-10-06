
package com.tesisiii.DAO;


import com.tesisiii.model.Project;
import java.util.List;

public interface ProjectInterface {
    public void addProject(Project p);
    public void removeProject(Project p);
    public Project getProject(Integer projectID);
    public void editProject (Project p);
    public List<Project> listProject();
    public List<Project> listProjectDeleted();
    public List<Project> listProjectCompleted();
    public List<Project> projectListByManager(String loginid);
    public Project getProjectByManager(String pmlogin);
    public void mergeProject(Project p);
}