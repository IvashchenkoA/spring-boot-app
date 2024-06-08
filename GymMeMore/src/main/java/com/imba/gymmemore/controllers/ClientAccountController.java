package com.imba.gymmemore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class ClientAccountController {

    @GetMapping
    public String getAcc(Model model){
        return "account";
    }

    @GetMapping("/details")
    public String getDetails(Model model){
        return "acc-details";
    }

    @GetMapping("/detalis/resign")
    public String getResign(){
        return "resign";
    }
    @DeleteMapping("/details/resign")
    public String resign(@PathVariable String password){
        return "redirect:home";
    }

    @GetMapping("/groupSchedule")
    public String getGroupSchedule(){
        return "group-schedule";
    }
    @GetMapping("/groupSchedule/{id}")
    public String getClassDetails(@PathVariable Long id){
        return "class-details";
    }

    @PostMapping("/groupSchedule")
    public String signForGroupClass(){
        return "redirect:/account/groupClass";
    }
    @GetMapping("/groupClass")
    public String confirmGrClass(){
        return "class-confirm";
    }
    @GetMapping("/yourSchedule")
    public String getYourSchedule(Model model){
        return "your-schedule";
    }
    @GetMapping("/yourSchedule/review")
    public String leaveReview(){
        return "review";
    }
    @PostMapping("/yourSchedule/review")
    public String submitReview(ReviewDTO reviewDTO){
        return "redirect:/account/yourSchedule";
    }
    @GetMapping("/yourSchedule/resign")
    public String getResignPage(){
        return "resign-class";
    }
    @DeleteMapping("/yourSchedule/resign")
    public String resignClass(@RequestParam Long id){
        return "redirect:/account/yourSchedule";
    }
    @GetMapping("/indiv")
    public String scheduleIndiv(){
        return "indiv-class";
    }
    @PostMapping("/indiv")
    public String submitIndiv(){
        return "redirect:/account";
    }
    @GetMapping("/offers")
    public String getOffers(){
        return "offers";
    }
}
