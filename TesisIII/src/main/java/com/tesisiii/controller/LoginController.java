package com.tesisiii.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    
    @GetMapping("/login")
    public String getLoginView(){
        return "loginForm";
    }
     @GetMapping("/default")
    public String getDefaultAfterLogin(Authentication auth, Model model){
        String role = auth.getAuthorities().toString();
        String login =auth.getName();
        if(role.contains("ADMIN")){
            return "redirect:/admin";
        }if(role.contains("PM")){
            model.addAttribute("login",login);
            return "redirect:/pm/"+login;
        }if(role.contains("USER")){
            return "redirect:/user/"+login;
        }else{
            return "loginForm";
        }
        }
}
