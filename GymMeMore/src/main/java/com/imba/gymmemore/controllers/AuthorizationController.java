package com.imba.gymmemore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorizationController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String log(Model model){
        return "redirect:/account";
    }

    @GetMapping("/join")
    public String getJoin(){
        return "joinPage";
    }
    @GetMapping("/signUp")
    public String signUp(Model model){
        return "signUp";
    }
    @PostMapping("/signUp")
    public String sign(Model model){
        return "redirect:/account";
    }
}
