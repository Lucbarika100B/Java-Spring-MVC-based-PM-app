
package com.tesisiii.controller;

import com.tesisiii.model.Project;
import com.tesisiii.model.ProjectPool;
import com.tesisiii.model.Task;
import com.tesisiii.model.Usuario;
import com.tesisiii.services.Services;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pm/{login}")
public class PmController {
    @Autowired
    private Services serv;
    
    
    @GetMapping ()
    public String pm(Authentication auth, @PathVariable("login") String pmlogin,@ModelAttribute("p") Project p, Model model){
        model.addAttribute("projectListByManager", serv.projectListByManager(pmlogin));
        model.addAttribute("p",new Project());
        return "pm";
    }
     @RequestMapping(path = "/PmUsersByProject", method = RequestMethod.POST)
    public ModelAndView UsersByProject(
            @ModelAttribute("p") Project p, 
            @RequestParam(name="ProjectName") String ProjectName, 
            @PathVariable("login") String pmlogin,
            BindingResult errors, Model model){
        

        model.addAttribute("listUserIdByManager", serv.listUserIdByManager(pmlogin));
        model.addAttribute("login",pmlogin);
        model.addAttribute("ProjectName",ProjectName);
        ProjectPool pp = serv.getPoolByManager(pmlogin);
        String poolIdOnUser = Integer.toString (pp.getId());
        model.addAttribute("poolIdOnUser",poolIdOnUser);
        model.addAttribute("listUserIdByManager", serv.listUserIdByManager(pmlogin));
        model.addAttribute("listUserAssigned", serv.listUserAssigned(pmlogin));
        model.addAttribute("Projectid", pp.getPid());
        ModelAndView UsersByProject = new ModelAndView("pmUsersByProject");
        return UsersByProject;
    }
    @RequestMapping(path = "/PmAssigneUsersToProject", method = RequestMethod.POST)
    public ModelAndView PmAssigneUsersToProject (@ModelAttribute("u") Project u, 
            @RequestParam("id") Integer id, 
            @PathVariable("login") String pmlogin,
            BindingResult errors, 
            Model model){
        System.out.println("PmAssigneUsersToProject");
        serv.assigneUserToPool(pmlogin, id);
        model.addAttribute("login",pmlogin);
        model.addAttribute("listUserIdByManager", serv.listUserIdByManager(pmlogin));
        model.addAttribute("listUserAssigned", serv.listUserAssigned(pmlogin));
        ModelAndView PmAssigneUsersToProject = new ModelAndView("pmUsersByProject");
        return PmAssigneUsersToProject;
    }
    
    @RequestMapping(path = "/PmUnassaingUsersToProject", method = RequestMethod.POST)
    public ModelAndView PmUnassaingUsersToProject (
            @ModelAttribute("u") Project u, 
            @RequestParam("id") Integer id, 
            @PathVariable("login") String pmlogin,
            BindingResult errors, 
            Model model){
        System.out.println("PmAssigneUsersToProject");
        serv.unAssigneUserToPool(id);
        model.addAttribute("listUserIdByManager", serv.listUserIdByManager(pmlogin));
        model.addAttribute("listUserAssigned", serv.listUserAssigned(pmlogin));
        ModelAndView PmUnassaingUsersToProject = new ModelAndView("pmUsersByProject");
        return PmUnassaingUsersToProject;
    }
    @RequestMapping(path = "/pmTasksUsersToProject", method = RequestMethod.GET)
    public ModelAndView pmTasksUsersToProject(
            @ModelAttribute("t") Task t, 
            @RequestParam(name="ProjectName") String ProjectName,
            @RequestParam(name="Projectid") int Projectid, 
            @RequestParam(name="id") int userId,
            @PathVariable("login") String pmlogin,
            BindingResult errors, Model model){
        Usuario u = serv.getAUsuario(userId);
        String uName =u.getFirstName();
        model.addAttribute("userName",uName);
        model.addAttribute("tasksListByUserInProject",serv.tasksListByUserInProject(pmlogin,userId, Projectid));
        model.addAttribute("login",pmlogin);
        model.addAttribute("ProjectName",ProjectName);
        model.addAttribute("Projectid",Projectid);
        ModelAndView pmTasksUsersToProject = new ModelAndView("pmTasksUsersToProject");
        return pmTasksUsersToProject;
    }
    
    @RequestMapping(path = "/pmTaskByProject", method = RequestMethod.GET)
    public String pmTaskByProject(
                Model model,
                @PathVariable("login") String pmlogin,
                @RequestParam(name="Projectid") int Projectid)
    {
        System.out.println("Projectid : " + Projectid);    
        model.addAttribute("t",new Task());
        model.addAttribute("Projectid", Projectid);
        model.addAttribute("listTasksByProyect", serv.listTasksByProyect(Projectid));
        model.addAttribute("listUserAssigned", serv.listUserAssigned(pmlogin));
        return "pmTaskByProject";
    }
     @RequestMapping(path = "/pmTaskByProject", method = RequestMethod.POST)
    public ModelAndView pmTaskByProjectII(
            @Valid @ModelAttribute("t") Task t,
            Model model,
            @PathVariable("login") String pmlogin,
            BindingResult errors)
    {   
        if(errors.hasErrors()){
                ModelAndView submitNewUser = new ModelAndView("pmTaskByProject");       
                submitNewUser.addObject("message","User not save");
                System.out.println("aqui estamos controlador POST hasErrors"
                        + errors.getAllErrors().get(0));  
                return submitNewUser;
            }
            serv.addTask(t);
        System.out.println("LLEGUE al pmTaskByProjectII POST");
            ModelAndView pmTaskByProjectII = new ModelAndView("pmTaskByProject");
            model.addAttribute("t",new Task());     
            model.addAttribute("listTasksByProyect", serv.listTasksByProyect(t.getTaskProject()));
            model.addAttribute("listUserAssigned", serv.listUserAssigned(pmlogin));
        return pmTaskByProjectII;
    }
    @RequestMapping(path = "/pmEditProject", method = RequestMethod.GET)
    public ModelAndView pmEditProject(
            @ModelAttribute("p") Project p,
            @PathVariable("login") String pmlogin,
            @RequestParam(name="Projectid") int Projectid,
            Model model)
    {   
        model.addAttribute("Projectid", Projectid);
        ModelAndView pmEditProject = new ModelAndView("pmEditProject");
        Project pselected = serv.getProject(Projectid);
        model.addAttribute("p",pselected);
        return pmEditProject;
    }
    @RequestMapping(path = "/pmEditProject", method = RequestMethod.POST)
    public ModelAndView  editProjectII (
            @ModelAttribute("p") Project p, 
            @RequestParam("Projectid") Integer projecto,
            @PathVariable("login") String pmlogin,
            Model model)
    {
        serv.updateProject(p);
        ModelAndView editProjectII = new ModelAndView("pm");
        model.addAttribute("projectListByManager", serv.projectListByManager(pmlogin));
        return editProjectII;
    }
    
    @RequestMapping(path = "/pmEditTask", method = RequestMethod.GET)
    public ModelAndView pmEditTask(
            @ModelAttribute("t") Task t,
            @PathVariable("login") String pmlogin,
            @RequestParam(name="taskProject") int taskProject,
            @RequestParam(name="taskId") int taskId,
            Model model)
    {   
        Task taskSelected = serv.getTask(taskId);
        model.addAttribute("t",taskSelected);
        model.addAttribute("listUserAssigned", serv.listUserAssigned(pmlogin));
        ModelAndView pmEditTask = new ModelAndView("pmEditTask");

        return pmEditTask;
    }
    @RequestMapping(path = "/pmEditTask", method = RequestMethod.POST)
    public ModelAndView pmEditTaskII(
            @ModelAttribute("t") Task t,
            @PathVariable("login") String pmlogin,
            @RequestParam(name="taskProject") int taskProject,
            @RequestParam(name="taskId") int taskId,
            Model model)
    {   
        Task taskSelected = serv.getTask(taskId);
        model.addAttribute("t",taskSelected);
        model.addAttribute("listUserAssigned", serv.listUserAssigned(pmlogin));
        serv.editTask(t);
        ModelAndView pmEditTaskII = new ModelAndView("pmTaskByProject");
        model.addAttribute("t",new Task());     
        model.addAttribute("listTasksByProyect", serv.listTasksByProyect(t.getTaskProject()));
        model.addAttribute("listUserAssigned", serv.listUserAssigned(pmlogin));    
        return pmEditTaskII;
    }
    
    @RequestMapping(path = "/pmRemoveTask", method = RequestMethod.GET)
    public ModelAndView pmRemoveTask(
            @ModelAttribute("t") Task t,
            @PathVariable("login") String pmlogin,
            @RequestParam(name="idselected") int tasIdkSelected,
            Model model)
    {  
        ModelAndView pmRemoveTask = new ModelAndView("ConfirmationPageTask");
        Task taskSelected = serv.getTask(tasIdkSelected);
        model.addAttribute("t",taskSelected);
        return pmRemoveTask;
    }
    
     @RequestMapping(path = "/pmRemoveTask", method = RequestMethod.POST)
    public ModelAndView pmRemoveTaskII(
            @ModelAttribute("t") Task t,
            @PathVariable("login") String pmlogin,
//            @RequestParam(name="idselected") int tasIdkSelected,
            Model model)
    {   
        serv.removeTask(t);
//        Task taskSelected = serv.getTask(tasIdkSelected);
        model.addAttribute("t",new Task());
        ModelAndView pmRemoveTaskII = new ModelAndView("pmTaskByProject");
        model.addAttribute("listTasksByProyect", serv.listTasksByProyect(t.getTaskProject()));
            model.addAttribute("listUserAssigned", serv.listUserAssigned(pmlogin));
        return pmRemoveTaskII;
    }
}
