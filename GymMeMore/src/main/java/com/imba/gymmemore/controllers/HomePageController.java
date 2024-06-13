package com.imba.gymmemore.controllers;

import com.imba.gymmemore.DTO.AddressDTO;
import com.imba.gymmemore.DTO.BranchDTO;
import com.imba.gymmemore.models.Address;
import com.imba.gymmemore.models.Branch;
import com.imba.gymmemore.services.HomePageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/branches/city/{city}")
    public String getBranchesByCity(@PathVariable String city, Model model){
        Iterable<Branch> branches;
        if(city != null && !city.isEmpty()){
            branches = homePageService.getBranchesByCity(city);
        }
        else{
            branches = homePageService.getAllBranches();
        }
        model.addAttribute("branches", branches);
        return "branches";
    }

    @PostMapping("/branches")
    public String branchesByCity(@RequestParam(required = false) String city, Model model){
        Iterable<Branch> branches;
        if(city != null && !city.isEmpty()){
            branches = homePageService.getBranchesByCity(city);
        }
        else{
           branches = homePageService.getAllBranches();
        }
        model.addAttribute("branches", branches);
        return "branches";
    }

    @GetMapping("/branches/{id}")
    public String getBranchDetails(@PathVariable Long id, Model model){
        try {
            model.addAttribute("id", id);
            Optional<Branch> b = homePageService.getBranchById(id);
            Optional<Address> address = homePageService.getAddressByBranchId(id);
            AddressDTO aDTO = new AddressDTO(address.get().getCity(), address.get().getStreet(), address.get().getHouseNumber());
            BranchDTO br = new BranchDTO(b.get().getName(), b.get().getDescription(), aDTO);
            model.addAttribute("branch", br);
            return "branchDetails";
        }
        catch(Exception e){
            return "";
        }
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
