
package com.tesisiii.services;

import com.tesisiii.DAO.ProjectInterface;
import com.tesisiii.DAO.ProjectsPoolInterface;
import com.tesisiii.DAO.TaskInterface;
import com.tesisiii.DAO.UserImp;
import com.tesisiii.DAO.UserInterface;
import com.tesisiii.model.Project;
import com.tesisiii.model.ProjectPool;
import com.tesisiii.model.Task;
import com.tesisiii.model.Usuario;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Services {
    @Autowired
    private ProjectInterface pdao;
    @Autowired
    private ProjectsPoolInterface ppdao;
    @Autowired
    private TaskInterface tdao;
    @Autowired
    private UserInterface udao;
    
    public void cretedUser(Usuario u){
        udao.addUser(u);
    }
     public void updateUser(Usuario u){
        udao.editUser(u);
    }
     public List<Usuario> listUser(){
        return udao.listUser();
    }
    public List<Usuario> listManager(){
        return udao.listManager();
    }
    
    public Usuario getAUsuario(Integer usuarioId){
        return udao.getUser(usuarioId);
    }
    public void editUser(Usuario u) {
        udao.editUser(u);
    }
    public void deletUser(Usuario u) {
        udao.removeUser(u);
    }
    public List<Usuario> listAllUser() {
        return udao.listAllUser();
    }
    public List<Project> listAllProject() {
        return pdao.listProject();
    }
     public List<ProjectPool> listPool() {
        return ppdao.listPool();
    }
     public void addProject(Project p) {
         pdao.addProject(p);
         if (p.getPooPId().equalsIgnoreCase("0")){
             ProjectPool pp = new ProjectPool();
             pp.setUserId(p.getManager());
             pp.setPid(Integer.toString(p.getId()));
             ppdao.addPool(pp);
             p.setPooPId(Integer.toString(pp.getId()));
             System.out.println("p.setPooPId(pp.getId()) : " + p.getPooPId());
             pdao.editProject(p);
         }
         
         
     }
     public Project getProject(Integer id) {
         return pdao.getProject(id);
     }
     public void updateProject(Project p) {
         pdao.editProject(p);
     }
     public ProjectPool getPool(Integer pPId) {
        return ppdao.getPool(pPId);
     }
     public void deletProject(Project p){
        System.out.println("estoy en el servicio deletProject"); 
        pdao.removeProject(p);
     }
     public List<Project> projectListByManager(String pmlogin) {
         Usuario u = udao.getUserIdByLogin(pmlogin);
         System.out.println("Integer loginid : " + u.getId());
         return pdao.projectListByManager(Integer.toString(u.getId()));
//         return pdao.listProject();
    }
    public List<Usuario> listUserIdByManager(String pmlogin){
        Usuario manager = udao.getUserIdByLogin(pmlogin);
        System.out.println("ManagerId : " + manager.getId());
        return udao.listUserIdByManager(manager.getId());
        
    }
    public List<Usuario> listUsuariosByPool(String pmlogin){
        Usuario manager = udao.getUserIdByLogin(pmlogin);
        return udao.listUsuariosByPool(manager.getPooPId());
    }
    public List<Project> listProjectDeleted() {
        return pdao.listProjectDeleted();
    }

    public List<Project> listProjectCompleted() {
        return pdao.listProjectCompleted();
    }
    public ProjectPool getPoolByManager(String pmlogin) {
        Usuario manager = udao.getUserIdByLogin(pmlogin);
        System.out.println("getUserIdByLogin : "+ manager.getFirstName());
        return ppdao.getPoolByManager(Integer.toString(manager.getId()));
    }
    public void assigneUserToPool (String pmlogin, int uid){
        Usuario u = udao.getUser(uid);
        System.out.println(" uid : " + uid);
        ProjectPool pp = getPoolByManager(pmlogin);
        System.out.println("pp.getId()" + pp.getId());
        u.setPooPId(Integer.toString(pp.getId()));
        udao.mergeUser(u);
    }
    public void unAssigneUserToPool (int uid){
        System.out.println("estoy en unAssigneUserToPool");
        System.out.println("uid : " + uid);
        Usuario u = udao.getUser(uid);
        System.out.println("u.getFirstName()" + u.getFirstName());
        u.setPooPId("0");
        udao.mergeUser(u);
    }
    public List<Usuario> listUserAssigned(String pmlogin){
        Usuario manager = udao.getUserIdByLogin(pmlogin);
        System.out.println("ManagerId : " + manager.getId());
        ProjectPool pp = getPoolByManager(pmlogin);
        String pooPIdUser =Integer.toString(pp.getId());
        return udao.listUserAssigned(manager.getId(),pooPIdUser);
        
    }
    public Project getProjectByManager(String pmlogin) {
        System.out.println("pmlogin : " + pmlogin);
        Usuario manager = udao.getUserIdByLogin(pmlogin);
        System.out.println("getProjectByManager : "+ manager.getFirstName());
        return pdao.getProjectByManager(Integer.toString(manager.getId()));
    }
    
    public List<Task> tasksListByUserInProject(String pmlogin, int userId, int Projectid){
        System.out.println("pmlogin : " + pmlogin);
        Usuario manager = udao.getUserIdByLogin(pmlogin);
//        String projectManager = manager.getLogin();
        Project p = pdao.getProject(Projectid);
        System.out.println(" estoy aqui tasksListByUserInProject");
        List<Task> tasksListByUserInProject  =tdao.UserProjectListTasks(p.getId(), userId);
        System.out.println("tasksListByUserInProject : " + tasksListByUserInProject);
        return tasksListByUserInProject;
    }
    
    public List<Task> listTasksByProyect(Integer Projectid){
        return tdao.listTasksByProyect(Projectid);
    }
    public void addTask(Task t){
        tdao.addTask(t);
    }
    public void mergeTask(Task t) {
        tdao.mergeTask(t);
    }
    public void mergeProject(Project p) {
        pdao.mergeProject(p);
    }
     public Task getTask(Integer id) {
        return tdao.getTask(id);
     }
     public void editTask(Task t) {
         tdao.editTask(t);
     }
     public List<Task> ListTasksByAUser(int uId) {
         return tdao.ListTasksByAUser(uId);
     }
     public void removeTask(Task t) {
         tdao.removeTask(t);
     }
     public Usuario getUserIdByLogin(String login) {
         return udao.getUserIdByLogin(login);
     }
}
