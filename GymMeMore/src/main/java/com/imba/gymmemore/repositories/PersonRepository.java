package com.imba.gymmemore.repositories;

import com.imba.gymmemore.models.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {
    @Query(value = "SELECT * FROM Person WHERE USERNAME=:username", nativeQuery = true)
    Person findClientByUsername(String username);
    Person findClientById(Long id);
    List<Person> findAll();
}
