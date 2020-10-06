
package com.tesisiii.DAO;


import com.tesisiii.model.ProjectPool;
import java.util.List;

public interface ProjectsPoolInterface {
    public void addPool(ProjectPool pp);
    public void removePool(ProjectPool pp);
    public ProjectPool getPool(Integer id);
    public void editPool(ProjectPool pp);
    public List<ProjectPool> listPool();
    public ProjectPool getPoolByManager(String id);
}
