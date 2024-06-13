package com.imba.gymmemore.controllers;

import com.imba.gymmemore.DTO.ClientDTO;
import com.imba.gymmemore.DTO.MembershipTypeDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String getJoin(Model model, @ModelAttribute("m") MembershipTypeDTO membershipTypeDTO){
        model.addAttribute("membershipType", membershipTypeDTO);
        model.addAttribute("client", new ClientDTO());
        return "join";
    }
    @PostMapping("/join")
    public String join(Model model, @ModelAttribute("membershipType") MembershipTypeDTO membershipTypeDTO,
                       @ModelAttribute("client") @Valid ClientDTO clientDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            model.addAttribute("membershipType", membershipTypeDTO);
            return "join";
        }

        return "redirect:/account";
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
