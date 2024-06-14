package com.imba.gymmemore.services;

import com.imba.gymmemore.DTO.ClientDTO;
import com.imba.gymmemore.DTO.LoginDTO;
import com.imba.gymmemore.DTO.MembershipTypeDTO;
import com.imba.gymmemore.models.*;
import com.imba.gymmemore.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDate;

@Service
public class AuthorizationService {
    private final PersonRepository personRepository;
    private final ClientRepository clientRepository;
    private final BankAccountRepository bankAccountRepository;
    private final MembershipRepository membershipRepository;
    private final MembershipTypeRepository membershipTypeRepository;
    private final ProfLevelRepository profLevelRepository;
    private static final int ACCOUNT_NUMBER_LENGTH = 25;
    private static final SecureRandom random = new SecureRandom();


    public AuthorizationService(PersonRepository personRepository,ClientRepository clientRepository, BankAccountRepository bankAccountRepository,
                                MembershipRepository membershipRepository, MembershipTypeRepository membershipTypeRepository, ProfLevelRepository profLevelRepository) {
        this.personRepository = personRepository;
        this.clientRepository = clientRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.membershipRepository = membershipRepository;
        this.membershipTypeRepository = membershipTypeRepository;
        this.profLevelRepository = profLevelRepository;
    }

    public String generateBankAccountNumber() {
        String accountNumber;
        do {
            accountNumber = generateRandomAccountNumber();
        } while (bankAccountRepository.existsByNumber(accountNumber));
        return accountNumber;
    }

    private String generateRandomAccountNumber() {
        StringBuilder accountNumber = new StringBuilder(ACCOUNT_NUMBER_LENGTH);
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            int digit = random.nextInt(10);
            accountNumber.append(digit);
        }
        return accountNumber.toString();
    }
    public ClientDTO authorizeClient(LoginDTO dto) {
        Person client = personRepository.findClientByUsername(dto.getUsername());
        if (client == null || !client.getPassword().equals(dto.getPassword())) {
            return null;
        }
        return new ClientDTO((Client) client);
    }

    public MembershipTypeDTO getMembershipTypeById(Long id){
        MembershipType m = membershipTypeRepository.findMembershipTypeById(id);
        return new MembershipTypeDTO(m.getId(), m.getName(), m.getDescription(),m.getPrice());
    }
    @Transactional
    public ClientDTO addClient(ClientDTO clientDTO, MembershipTypeDTO membershipTypeDTO){
        String num = generateBankAccountNumber();
        BankAccount bankAccount = new BankAccount(num, 0.0);
        bankAccount = bankAccountRepository.save(bankAccount);
        MembershipType membershipType = membershipTypeRepository.findById(membershipTypeDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid membership type ID"));
        Membership membership = new Membership(LocalDate.now(), membershipType);
        membership = membershipRepository.save(membership);
        Client client = clientDTO.map();
        client.setBankAccount(bankAccount);
        client.setMembership(membership);

        ProfLevel profLevel = profLevelRepository.findProfLevelByName("Beginner");
        client.setProfLevel(profLevel);

        client.setJoinDate(LocalDate.now());

        Client newClient = clientRepository.save(client);

        return new ClientDTO(newClient);
    }
}
