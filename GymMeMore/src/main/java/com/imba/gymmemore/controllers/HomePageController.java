package com.imba.gymmemore.controllers;

import com.imba.gymmemore.models.Branch;
import com.imba.gymmemore.services.HomePageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomePageController {
    private final HomePageService homePageService;

    public HomePageController(HomePageService homePageService) {
        this.homePageService = homePageService;
    }

    @GetMapping
    public String getHome(){
        return "home";
    }

    @GetMapping("/branches")
    public String getBranches(Model model){
        List<String> cities = homePageService.getAllCities();
        model.addAttribute("cities",cities);
        return "branches";
    }

    @PostMapping("/branches")
    public String branchesByCity(@RequestParam(required = false) String city, Model model){
        Iterable<Branch> branches;
        if(city != null && !city.isEmpty()){
            branches = homePageService.getAllBranches();
        }
        else{
           branches = homePageService.getBranchesByCity(city);
        }
        model.addAttribute("branches", branches);
        return "branches";
    }

    @GetMapping("branches/{id}")
    public String getBranchDetails(@RequestParam Long id, Model model){
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
