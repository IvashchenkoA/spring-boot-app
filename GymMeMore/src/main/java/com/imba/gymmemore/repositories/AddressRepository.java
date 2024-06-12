package com.imba.gymmemore.repositories;

import com.imba.gymmemore.models.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long> {
     @Query(value = "SELECT DISTINCT city FROM Address", nativeQuery = true)
     List<String> findAllCities();
}
