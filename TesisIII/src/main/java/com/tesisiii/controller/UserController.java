
package com.tesisiii.controller;

import com.tesisiii.model.Task;
import com.tesisiii.model.Usuario;
import com.tesisiii.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/user/{login}")
@Controller
public class UserController {
    
    @Autowired
    private Services serv;
    
    @RequestMapping("")
    public String user(
            @ModelAttribute("t") Task t,
            @PathVariable("login") String pmlogin,
            Model model)
    {
        Usuario userSelected = serv.getUserIdByLogin(pmlogin);
        model.addAttribute("userName",userSelected.getFirstName());
        model.addAttribute("ListTasksByAUser",serv.ListTasksByAUser(userSelected.getId()));
        return "user";
    }
    
    @RequestMapping(path = "/uEditTask", method = RequestMethod.GET)
    public ModelAndView pmEditTask(
            @ModelAttribute("t") Task t,
           @PathVariable("login") String pmlogin,
          @RequestParam(name="taskProject") int taskProject,
           @RequestParam(name="taskId") int taskId,
            Model model)
    {   
        System.out.println("entre al GET del edit");
       model.addAttribute("login",pmlogin);
       Task taskSelected = serv.getTask(taskId);
        model.addAttribute("t",taskSelected);
        ModelAndView pmEditTask = new ModelAndView("uEditTask");
        return pmEditTask;
    }
    @RequestMapping(path = "/uEditTask", method = RequestMethod.POST)
    public String pmEditTaskII(
            @ModelAttribute("t") Task t,
            @PathVariable("login") String pmlogin,
            @RequestParam(name="taskProject") int taskProject,
            @RequestParam(name="taskId") int taskId,
            Model model)
    {   
        serv.editTask(t);  
        Usuario userSelected = serv.getUserIdByLogin(pmlogin);
        model.addAttribute("ListTasksByAUser",serv.ListTasksByAUser(userSelected.getId()));
        return "user";
    }
}
