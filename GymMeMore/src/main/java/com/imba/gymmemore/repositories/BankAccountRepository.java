package com.imba.gymmemore.repositories;

import com.imba.gymmemore.models.BankAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {
    boolean existsByNumber(String number);
    @Query(value = "SELECT number FROM BANK_ACCOUNT B JOIN PERSON P ON B.ID=P.BANK_ACCOUNT_ID WHERE P.ID=:id", nativeQuery = true)
    String findBankAccountNumberByClientId(Long id);
    BankAccount findBankAccountByClientId(Long id);
}
