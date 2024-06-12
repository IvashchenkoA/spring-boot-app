package com.imba.gymmemore.repositories;

import com.imba.gymmemore.models.Branch;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BranchRepository extends CrudRepository<Branch, Long> {
    List<Branch> getBranchByAddress_City(String city);
}
