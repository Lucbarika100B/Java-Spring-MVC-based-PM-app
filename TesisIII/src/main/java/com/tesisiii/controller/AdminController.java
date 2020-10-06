package com.tesisiii.controller;


import com.tesisiii.model.Project;
import com.tesisiii.model.Usuario;
import com.tesisiii.services.Services;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private Services serv;
    
    @GetMapping
    public String admin(Model model){
        return "admin";
    }

    public String currentUserName(Authentication auth) {
        return auth.getName();
    }
                    //    PROCESOS GET
    
    @RequestMapping(path = "/AddUser", method = RequestMethod.GET)
    public String submitNewUserII(Model model){
            model.addAttribute("u", new Usuario());     
            model.addAttribute("listUser", serv.listUser());
            model.addAttribute("managerList", serv.listManager());
            System.out.println("aqui estamos controlador GET");
        return "AddUser";
    }
    
    @RequestMapping(path = "/removeUsuario", method = RequestMethod.GET)
    public ModelAndView removeUser(@ModelAttribute("u") Usuario u, @RequestParam("idselected") Integer usuario,
            BindingResult errors, Model model){
        Usuario usuarioSelected = serv.getAUsuario(usuario);
        model.addAttribute("u",usuarioSelected);
        System.out.println("usuarioSelected : "+ usuarioSelected.getId() );
         ModelAndView removeUser = new ModelAndView("ConfirmationPage");
        return removeUser;
    }
    
    @RequestMapping(path = "/listAllUser", method = RequestMethod.GET)
    public String listAllUser(Model model){
        model.addAttribute("listAllUser", serv.listAllUser());
        return "listAllUser";
    }
    
    @RequestMapping(path = "/AProject", method = RequestMethod.GET)
    public String submitNewProject(Model model){
            model.addAttribute("p", new Project());    
            model.addAttribute("listPool", serv.listPool());
            model.addAttribute("managerList", serv.listManager());
            model.addAttribute("listAllProject", serv.listAllProject());
            System.out.println("aqui estamos controlador  AProject GET");
        return "AProject";
    }
    
    @RequestMapping(path = "/editUsuario", method = RequestMethod.GET)
    public ModelAndView editUser(@ModelAttribute("u") Usuario u, @RequestParam("idselected") Integer usuario,
            BindingResult errors, Model model){
           
        if(errors.hasErrors()){
                ModelAndView submitNewUser = new ModelAndView("editUsuario");       
                submitNewUser.addObject("message","User not save");
                System.out.println("aqui estamos controlador edit POST hasErrors"
                        + errors.getAllErrors().get(0));  
                return submitNewUser;
            }
        System.out.println("aqui estamos controlador GET del edit"); 
            Usuario usuarioSelected = serv.getAUsuario(usuario);
            model.addAttribute("u",usuarioSelected);
            model.addAttribute("listUser", serv.listUser());
            model.addAttribute("managerList", serv.listManager());
            System.out.println("usuarioSelected : " + usuarioSelected.getFirstName());
//            serv.updateUser(usuarioSelected);
            ModelAndView editUser = new ModelAndView("editUsuario");
        return editUser;
    }
    
    @RequestMapping(path = "/editProject", method = RequestMethod.GET)
    public ModelAndView editOriject(@ModelAttribute("p") Project p, @RequestParam("idselected") Integer projecto,
            BindingResult errors, Model model){
           
        if(errors.hasErrors()){
                ModelAndView editProject = new ModelAndView("editProject");       
                editProject.addObject("message","User not save");
                System.out.println("aqui estamos controlador editProject GET hasErrors"
                        + errors.getAllErrors().get(0));  
                return editProject;
            }
        System.out.println("aqui estamos controlador editProject GET del edit"); 
            Project projectoSelected = serv.getProject(projecto);
            model.addAttribute("p",projectoSelected);
            model.addAttribute("managerList", serv.listManager());
            System.out.println("projectoSelected : " + projectoSelected.getName());
            ModelAndView editProject = new ModelAndView("editProject");
        return editProject;
    }
    
    @RequestMapping(path = "/removeProject", method = RequestMethod.GET)
    public ModelAndView removeProject(@ModelAttribute("p") Project p, @RequestParam("idselected") Integer projecto , @RequestParam("poolID") Integer poolid,
            BindingResult errors, Model model){
        Project projectSelected = serv.getProject(projecto);
        Integer poolID = poolid;
        model.addAttribute("p",projectSelected);
        model.addAttribute("poolID",poolID);
        System.out.println("projectSelected : "+ projectSelected.getId() );
         ModelAndView removeProject = new ModelAndView("ConfirmationPageProject");
        return removeProject;
    }
    
    
                    //    PROCESOS POST
    
    @RequestMapping(path = "/editProject", method = RequestMethod.POST)
    public ModelAndView  editProjectII (@ModelAttribute("p") Project p, @RequestParam("idselected") Integer projecto,
            BindingResult errors, Model model){
        System.out.println("p.getId() : " + p.getId() );
        serv.updateProject(p);
        model.addAttribute("p", new Project());     
        model.addAttribute("managerList", serv.listManager());
        model.addAttribute("listAllProject", serv.listAllProject());
        ModelAndView editProjectII = new ModelAndView("AProject");
        return editProjectII;
    }
    
    @RequestMapping(path = "/editUsuario", method = RequestMethod.POST)
    public ModelAndView  editUserII(@ModelAttribute("u") Usuario u, @RequestParam("idselected") Integer usuario,
            BindingResult errors, Model model){
        System.out.println("u.getId() : " + u.getId() );
        serv.updateUser(u);
        model.addAttribute("u", new Usuario());     
        model.addAttribute("listUser", serv.listUser());
        model.addAttribute("managerList", serv.listManager());
        ModelAndView editUserII = new ModelAndView("AddUser");
        return editUserII;
    }
    
    @RequestMapping(path = "/removeUsuario", method = RequestMethod.POST)
    public ModelAndView removeUserII(@ModelAttribute("u") Usuario u, Model model){
        System.out.println(" DELECT u.getID() : "+ u.getId() );
        System.out.println(" DELECT u.getLogin() : "+ u.getLogin() );
        serv.deletUser(u);
        model.addAttribute("u", new Usuario());     
        model.addAttribute("listUser", serv.listUser());
        model.addAttribute("managerList", serv.listManager());
        ModelAndView removeUserII = new ModelAndView("AddUser");
        return removeUserII;
    }
    
    @RequestMapping(path = "/removeProject", method = RequestMethod.POST)
    public ModelAndView removeProjectII(@ModelAttribute("p") Project p, @RequestParam("pooPId") Integer poolid,Model model){
        System.out.println("estoy en l remove project POST");
        System.out.println(" p id : " + p.getId());
        serv.deletProject(p);
        model.addAttribute("p", new Project());    
        model.addAttribute("listPool", serv.listPool());
        model.addAttribute("managerList", serv.listManager());
        model.addAttribute("listAllProject", serv.listAllProject());
        ModelAndView removeUserII = new ModelAndView("AProject");
        return removeUserII;
    }
    
    @RequestMapping(path = "/AProject", method = RequestMethod.POST)
    public ModelAndView submitNewProjectII(@Valid @ModelAttribute("p") Project p, 
            BindingResult errors, Model model){
        if(errors.hasErrors()){
                ModelAndView submitNewProjectII = new ModelAndView("AProject");       
                submitNewProjectII.addObject("message","Project not save");
                System.out.println("aqui estamos controlador AProject POST hasErrors"
                        + errors.getAllErrors().get(0));  
                return submitNewProjectII;            
            }
        System.out.println("aqui estamos controlador AProject POST"); 
        serv.addProject(p);
        ModelAndView submitNewProjectII = new ModelAndView("AProject");
        model.addAttribute("p", new Project());
        model.addAttribute("listPool", serv.listPool());
        model.addAttribute("managerList", serv.listManager());
        model.addAttribute("listAllProject", serv.listAllProject());
        return submitNewProjectII;
    }
    
    @RequestMapping(path = "/AddUser", method = RequestMethod.POST)
    public ModelAndView submitNewUser(@Valid @ModelAttribute("u") Usuario u, 
            BindingResult errors, Model model){   
        if(errors.hasErrors()){
                ModelAndView submitNewUser = new ModelAndView("AddUser");       
                submitNewUser.addObject("message","User not save");
                System.out.println("aqui estamos controlador POST hasErrors"
                        + errors.getAllErrors().get(0));  
                return submitNewUser;
            }
            ModelAndView submitNewUser = new ModelAndView("AddUser");
            
            serv.cretedUser(u);
            model.addAttribute("u", new Usuario());
            model.addAttribute("listUser", serv.listUser());
            model.addAttribute("managerList", serv.listManager());
        return submitNewUser;
    }
    
    @RequestMapping(path = "/AListAllProject", method = RequestMethod.POST)
    public ModelAndView AListAllProject(@Valid @ModelAttribute("p") Project p, 
            BindingResult errors, Model model){
        model.addAttribute("listAllProject", serv.listAllProject());
        ModelAndView AListAllProject = new ModelAndView("AListAllProject");
        return AListAllProject;
    }
    
    @RequestMapping(path = "/AListProjectCompleted", method = RequestMethod.POST)
    public ModelAndView AListAllProjectCompleted(@Valid @ModelAttribute("p") Project p, 
            BindingResult errors, Model model){
        model.addAttribute("listProjectCompleted", serv.listProjectCompleted());
        ModelAndView AListAllProjectCompleted = new ModelAndView("AListProjectCompleted");
        return AListAllProjectCompleted;
    }
    @RequestMapping(path = "/AListProjectDeleted", method = RequestMethod.POST)
    public ModelAndView AListProjectDeleted(@Valid @ModelAttribute("p") Project p, 
            BindingResult errors, Model model){
        model.addAttribute("listProjectDeleted", serv.listProjectDeleted());
        ModelAndView AListProjectDeleted = new ModelAndView("AListProjectDeleted");
        return AListProjectDeleted;
    }
}
