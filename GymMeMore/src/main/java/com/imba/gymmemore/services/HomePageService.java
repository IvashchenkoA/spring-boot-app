package com.imba.gymmemore.services;

import com.imba.gymmemore.models.Branch;
import com.imba.gymmemore.repositories.AddressRepository;
import com.imba.gymmemore.repositories.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomePageService {
    private final BranchRepository branchRepository;
    private final AddressRepository addressRepository;

    public HomePageService(BranchRepository branchRepository, AddressRepository addressRepository) {
        this.branchRepository = branchRepository;
        this.addressRepository = addressRepository;
    }

    public List<String> getAllCities(){
        return addressRepository.findAllCities();
    }

    public Iterable<Branch> getAllBranches(){
        return branchRepository.findAll();
    }

    public List<Branch> getBranchesByCity(String city){
        return branchRepository.getBranchByAddress_City(city);
    }
}
