package com.imba.gymmemore.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imba.gymmemore.DTO.AddressDTO;
import com.imba.gymmemore.DTO.BranchDTO;
import com.imba.gymmemore.models.Address;
import com.imba.gymmemore.models.Branch;
import com.imba.gymmemore.services.GoogleMapsService;
import com.imba.gymmemore.services.HomePageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home")
public class HomePageController {
    private final HomePageService homePageService;
    private final GoogleMapsService googleMapsService;
    private final ObjectMapper objectMapper;

    public HomePageController(HomePageService homePageService, GoogleMapsService googleMapsService, ObjectMapper objectMapper) {
        this.homePageService = homePageService;
        this.googleMapsService = googleMapsService;
        this.objectMapper = objectMapper;
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
    public String calculateRoute(@PathVariable Long id, @RequestParam String userAddress, Model model) throws IOException {
        Optional<Branch> b = homePageService.getBranchById(id);

        if (b.isPresent()) {
            Address branchAddress = b.get().getAddress();
            AddressDTO address = new AddressDTO(branchAddress.getCity(), branchAddress.getStreet(), branchAddress.getHouseNumber());
            String destination = address.getCity() + ", " + address.getStreet() + " " + address.getHouseNumber();

            if (!googleMapsService.isValidAddress(userAddress)) {
                model.addAttribute("error", "Invalid address. Please enter a valid address.");
                return "branchDetails";
            }

            try {
                String route = googleMapsService.getRoute(userAddress, destination);
                String mapUrl = googleMapsService.generateStaticMapWithRoute(userAddress, destination);

                model.addAttribute("route", route);
                model.addAttribute("mapUrl", mapUrl);
                model.addAttribute("branch", new BranchDTO(b.get().getName(), b.get().getDescription(), address));
            } catch (IOException e) {
                model.addAttribute("error", "Error calculating route. Please try again.");
                return "branchDetails";
            }
        } else {
            model.addAttribute("error", "Branch not found.");
            return "branchDetails";
        }
        return "branchDetails";
    }

    @GetMapping("/autocomplete")
    @ResponseBody
    public JsonNode autocompleteAddress(@RequestParam String input) {
        try {
            return googleMapsService.autocompleteAddress(input);
        } catch (IOException e) {
            return objectMapper.createObjectNode().putArray("predictions");
        }
    }

}
