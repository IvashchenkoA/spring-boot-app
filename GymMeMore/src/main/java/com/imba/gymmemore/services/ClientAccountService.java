package com.imba.gymmemore.services;

import com.imba.gymmemore.DTO.AccountDetailsDTO;
import com.imba.gymmemore.DTO.GroupClassDTO;
import com.imba.gymmemore.models.*;
import com.imba.gymmemore.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClientAccountService {
    private final ProfLevelRepository profLevelRepository;
    private final PersonRepository personRepository;
    private final BankAccountRepository bankAccountRepository;
    private final MembershipRepository membershipRepository;
    private final GroupClassRepository groupClassRepository;

    public ClientAccountService(GroupClassRepository groupClassRepository, ProfLevelRepository profLevelRepository, PersonRepository personRepository, BankAccountRepository bankAccountRepository, MembershipRepository membershipRepository) {
        this.groupClassRepository = groupClassRepository;
        this.profLevelRepository = profLevelRepository;
        this.personRepository = personRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.membershipRepository = membershipRepository;
    }

    public AccountDetailsDTO getAccountDetailsById(Long id){
        Client client =(Client) personRepository.findClientById(id);
        String account = bankAccountRepository.findBankAccountNumberByClientId(id);
        Membership membership = membershipRepository.findMembershipByClientId(id);
        MembershipType memType = membership.getMembershipType();
        ProfLevel level = profLevelRepository.findProfLevelByClientId(id);
        return new AccountDetailsDTO(client.getFirstName(),account, memType.getName(),
                membership.getExpiryDate(), level.getName(), level.getDescription(),client.getClassesCount());
    }
    public Map<String, GroupClassDTO> getGroupSchedule() {
        List<GroupClass> groupClasses = (List<GroupClass>) groupClassRepository.findAll();
        Map<String, GroupClassDTO> schedule = new HashMap<>();
        for (GroupClass groupClass : groupClasses) {
            String dayHourKey = getDayHourKey(groupClass.getScheduledTime());
            GroupClassDTO dto = new GroupClassDTO(groupClass);
            schedule.put(dayHourKey, dto);
        }

        return schedule;
    }

    private String getDayHourKey(LocalDateTime scheduledTime) {
        String dayOfWeek = scheduledTime.getDayOfWeek().toString();
        String hour = scheduledTime.getHour() + ":00";
        return dayOfWeek + "_" + hour;
    }

    public ClassDetailsDTO getClassDetails(Long classId) {
        GroupClass groupClass = groupClassRepository.findById(classId).orElseThrow(() -> new ResourceNotFoundException("Class not found"));
        ClassType classType = classTypeRepository.findById(groupClass.getClassTypeId()).orElseThrow(() -> new ResourceNotFoundException("Class type not found"));
        Coach coach = coachRepository.findById(groupClass.getCoachId()).orElseThrow(() -> new ResourceNotFoundException("Coach not found"));

        return new ClassDetailsDTO(classId, coach.getName(), coach.getBranch(), groupClass.getPopularity(), groupClass.getRating());
    }

    public boolean signUpForClass(Long classId, String username) {
        Long clientId = clientRepository.findByUsername(username).getId();
        ClientGroupClass clientGroupClass = new ClientGroupClass(classId, clientId);
        clientGroupClassRepository.save(clientGroupClass);
        return true;
    }
    @Transactional
    public boolean resignMembership(Long id, String password) {
        Client client = (Client) personRepository.findById(id).orElse(null);
        if (client != null && client.getPassword().equals(password)) {
            Membership membership = membershipRepository.findMembershipByClientId(id);
            if (membership != null) {
                membershipRepository.delete(membership);
            }
            BankAccount bankAccount = bankAccountRepository.findBankAccountByClientId(id);
            if (bankAccount != null) {
                bankAccountRepository.delete(bankAccount);
            }
            personRepository.delete(client);
            return true;
        }
        return false;
    }
}
