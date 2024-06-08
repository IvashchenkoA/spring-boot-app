package com.imba.gymmemore.repositories;

import com.imba.gymmemore.models.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
