package com.imba.gymmemore.controllers;

import com.imba.gymmemore.DTO.MembershipTypeDTO;
import com.imba.gymmemore.services.MembershipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/membership")
public class MembershipController {
    private final MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @GetMapping
    public String getMembershipsPage(Model model){
        List<MembershipTypeDTO> list = membershipService.getAllMembershipTypes();
        model.addAttribute("memberships", list);
        return "memberships";
    }
}
