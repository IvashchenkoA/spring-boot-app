package com.imba.gymmemore.controllers;

import com.imba.gymmemore.DTO.ClientDTO;
import com.imba.gymmemore.DTO.LoginDTO;
import com.imba.gymmemore.DTO.MembershipTypeDTO;
import com.imba.gymmemore.models.Client;
import com.imba.gymmemore.services.AuthorizationService;
import com.imba.gymmemore.services.MembershipService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthorizationController {
    private final AuthorizationService authorizationService;
    private final MembershipService membershipService;

    public AuthorizationController(AuthorizationService authorizationService, MembershipService membershipService) {
        this.authorizationService = authorizationService;
        this.membershipService = membershipService;
    }

    @GetMapping("/join")
    public String getJoin(Model model, @ModelAttribute("m") MembershipTypeDTO membershipTypeDTO){
        model.addAttribute("membershipType", membershipTypeDTO);
        model.addAttribute("client", new ClientDTO());
        return "join";
    }
    @PostMapping("/join")
    public String join(Model model, @RequestParam("membershipType.id") Long id,
                       @ModelAttribute("client") @Valid ClientDTO clientDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        MembershipTypeDTO memb = authorizationService.getMembershipTypeById(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("membershipType", memb);
            return "join";
        }
        ClientDTO newClientDTO = authorizationService.addClient(clientDTO, memb);
        model.addAttribute("newClient", newClientDTO);
        redirectAttributes.addFlashAttribute("newClient", newClientDTO);
        return "redirect:/account";
    }
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("client", new LoginDTO());
        return "login";
    }
    @PostMapping("/login")
    public String doLogin(Model model, @ModelAttribute("client") LoginDTO client, RedirectAttributes redirectAttributes) {
        ClientDTO cl = authorizationService.authorizeClient(client);
        if (cl == null) {
            model.addAttribute("error", "Incorrect username or password.");
            return "login";
        }
        redirectAttributes.addFlashAttribute("newClient", cl);
        return "redirect:/account";
    }
}
