package com.imba.gymmemore.controllers;

import com.imba.gymmemore.DTO.*;
import com.imba.gymmemore.models.Coach;
import com.imba.gymmemore.models.GroupClass;
import com.imba.gymmemore.services.ClientAccountService;
import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/account")
public class ClientAccountController {
    private final ClientAccountService clientAccountService;

    public ClientAccountController(ClientAccountService clientAccountService) {
        this.clientAccountService = clientAccountService;
    }

    @GetMapping
    //@PreAuthorize("hasRole('CLIENT')")
    public String account(Model model, @ModelAttribute("newClient") ClientDTO newClientDTO) {
        model.addAttribute("newClient", newClientDTO);
        return "account";
    }
    @GetMapping("/details")
   // @PreAuthorize("hasRole('CLIENT')")
    public String getDetails(Model model, @RequestParam("id") Long id){
        AccountDetailsDTO details = clientAccountService.getAccountDetailsById(id);
        model.addAttribute("details", details);
        model.addAttribute("id", id);
        return "account-details";
    }

    @PostMapping("/resign")
    //@PreAuthorize("hasRole('CLIENT')")
    public String resignMembership(@RequestParam("id") Long id, @RequestParam("password") String password, Model model) {
        boolean success = clientAccountService.resignMembership(id, password);
        if (success) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid password. Please try again.");
            return "account-details";
        }
    }

    @GetMapping("/group-schedule")
   // @PreAuthorize("hasRole('CLIENT')")
    public String getGroupSchedule(Model model, @RequestParam("id") Long id) {
        model.addAttribute("classes", clientAccountService.getFutureClasses());
        model.addAttribute("clientId", id);
        return "group-schedule";
    }

    @GetMapping("/group-schedule/details/{classId}")
   // @PreAuthorize("hasRole('CLIENT')")
    public String getClassDetails(@PathVariable("classId") Long classId, @RequestParam("clientId") Long clId, Model model) {
        try {
            model.addAttribute("classDetails", clientAccountService.getClassDetails(classId));
            model.addAttribute("clientId", clId);
        } catch (IOException e) {
            model.addAttribute("error", "Class not found");
            return "class-details";
        }
        return "class-details";
    }
    @PostMapping("/group-schedule/signup")
    //@PreAuthorize("hasRole('CLIENT')")
    public String enrollInClass(@RequestParam("clientId") Long clientId, @RequestParam("classId") Long classId,RedirectAttributes redirectAttributes, Model model) throws IOException {
        boolean enrolled = false;
        try {
            enrolled = clientAccountService.signUpForClass(clientId, classId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (enrolled) {
            redirectAttributes.addFlashAttribute("newClient", clientAccountService.getClientById(clientId));
            return "redirect:/account";
        } else {
            model.addAttribute("classDetails", clientAccountService.getClassDetails(classId));
            model.addAttribute("error", "the class is already full.");
            return "class-details";
        }
    }
    @GetMapping("/your-schedule")
    //@PreAuthorize("hasRole('CLIENT')")
    public String getPersonalSchedule(@RequestParam Long id, Model model) {
        model.addAttribute("classes", clientAccountService.getFutureClassesForClient(id));
        model.addAttribute("clientId", id);
        return "your-schedule";
    }
    @GetMapping("/leave-review")
   // @PreAuthorize("hasRole('CLIENT')")
    public String leaveReview(@RequestParam("clientId") Long id, Model model) {
        List<ReviewDTO> reviews = clientAccountService.getPastClassesForReview(id);
        model.addAttribute("clientId", id);
        if (reviews.isEmpty()) {
            model.addAttribute("message", "No reviews to leave.");
        } else {
            model.addAttribute("reviews", reviews);
        }
        return "leave-review";
    }

    @PostMapping("/submit-review")
   // @PreAuthorize("hasRole('CLIENT')")
    public String submitReview(@RequestParam Long clientId, @RequestParam Long classId, @RequestParam int rating, @RequestParam(required = false) String feedback, Model model) {
        try {
            clientAccountService.submitReview(clientId, classId, rating, feedback);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/account/your-schedule?id=" + clientId;
    }


    @GetMapping("/indiv")
   // @PreAuthorize("hasRole('CLIENT')")
    public String scheduleIndividualClass(@RequestParam("id") Long id, Model model) {
        model.addAttribute("clientId", id);
        return "schedule-indiv";
    }

    @PostMapping("/indiv/show-available-coaches")
   // @PreAuthorize("hasRole('CLIENT')")
    public String showAvailableCoaches(@RequestParam Long clientId, @RequestParam String date, @RequestParam String time, Model model) {
        LocalDateTime startTime = LocalDateTime.parse(date + "T" + time);
        LocalDateTime endTime = startTime.plusHours(1);

        List<Coach> availableCoaches = clientAccountService.getAvailableCoaches(startTime, endTime);

        model.addAttribute("coaches", availableCoaches);
        model.addAttribute("clientId", clientId);
        model.addAttribute("startTime", startTime.toString());
        return "available-coaches";
    }

    @PostMapping("/indiv/schedule")
   // @PreAuthorize("hasRole('CLIENT')")
    public String scheduleSession(@RequestParam Long clientId, @RequestParam Long coachId, @RequestParam String startTime, Model model) {
        LocalDateTime scheduledTime = LocalDateTime.parse(startTime);
        clientAccountService.scheduleIndividualSession(clientId, coachId, scheduledTime);
        return "redirect:/account/your-schedule?id=" + clientId;
    }
    /*@GetMapping("/offers")
    public String getOffers(){
        return "offers";
    }*/
}
