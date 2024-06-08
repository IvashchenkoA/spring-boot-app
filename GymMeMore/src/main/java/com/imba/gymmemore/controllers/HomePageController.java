package com.imba.gymmemore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("gym-me-more/home")
public class HomePageController {
    @GetMapping
    public String getHome(){
        return "home";
    }

    @GetMapping("/branches")
    public String getBranches(){
        return "branches";
    }

    @GetMapping("branches/{id}")
    public String getBranchDetails(){
        return "branchDetails";
    }

    @GetMapping("/branches/{id}/route")
    public String getRoute(){
        return "route";
    }

    @PostMapping("/branches/{id}/route")
    public String calcRoute(Model model){
        return "route";
    }
}
