package com.imba.gymmemore.services;

import com.imba.gymmemore.models.Address;
import com.imba.gymmemore.models.Branch;
import com.imba.gymmemore.repositories.AddressRepository;
import com.imba.gymmemore.repositories.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Branch> getBranchById(Long id){
        return branchRepository.findById(id);
    }

    public Optional<Address> getAddressByBranchId(Long id){
        return addressRepository.getAddressByBranchId(id);
    }
}
