package com.imba.gymmemore.repositories;

import com.imba.gymmemore.models.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {
}
