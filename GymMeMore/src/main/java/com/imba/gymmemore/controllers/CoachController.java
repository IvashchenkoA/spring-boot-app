package com.imba.gymmemore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account/coach")
public class CoachController {
    @GetMapping("/scheduleclass")
    public String scheduleClassPage(Model model) {
        return "scheduleClass";
    }

    @PostMapping("/scheduleclass")
    public String scheduleClass(/*ScheduleClassDto scheduleClassDto*/) {
        return "redirect:/account/coach/details";
    }

    @GetMapping("/details")
    public String coachDetails(Model model) {
        return "coachDetails";
    }
}
