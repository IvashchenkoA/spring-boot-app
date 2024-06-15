
package com.imba.gymmemore.controllers;

import com.imba.gymmemore.DTO.CoachDetailsDTO;
import com.imba.gymmemore.models.Coach;
import com.imba.gymmemore.services.CoachService;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/coach")
public class CoachController {

    private final CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }
    @GetMapping
    public String getCoach(Model model, @ModelAttribute("client") Coach coach){
        model.addAttribute("coach", coach);
        return "coach-account";
    }
    @GetMapping("/details")
    //@PreAuthorize("hasRole('COACH')")
    public String coachDetails(Model model, @RequestParam("id") Long id) {
        CoachDetailsDTO details = coachService.getCoachDetails(id);
        model.addAttribute("details", details);
        model.addAttribute("id", id);
        return "coachDetails";
    }
}

