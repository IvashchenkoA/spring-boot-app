package com.imba.gymmemore.controllers;

import com.imba.gymmemore.DTO.AccountDetailsDTO;
import com.imba.gymmemore.DTO.ClientDTO;
import com.imba.gymmemore.DTO.GroupClassDTO;
import com.imba.gymmemore.services.ClientAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String account(Model model, @ModelAttribute("newClient") ClientDTO newClientDTO) {
        model.addAttribute("newClient", newClientDTO);
        return "account";
    }
    @GetMapping("/details")
    public String getDetails(Model model, @RequestParam("id") Long id){
        AccountDetailsDTO details = clientAccountService.getAccountDetailsById(id);
        model.addAttribute("details", details);
        model.addAttribute("id", id);
        return "account-details";
    }

    @PostMapping("/resign")
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
    public String getGroupSchedule(Model model) {
        List<String> daysOfWeek = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        List<String> hours = IntStream.rangeClosed(10, 17)
                .mapToObj(hour -> hour + ":00")
                .collect(Collectors.toList());

        Map<String, GroupClassDTO> groupSchedule = clientAccountService.getGroupSchedule();

        model.addAttribute("daysOfWeek", daysOfWeek);
        model.addAttribute("hours", hours);
        model.addAttribute("groupSchedule", groupSchedule);
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
    public String submitReview(/*ReviewDTO reviewDTO*/){
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
