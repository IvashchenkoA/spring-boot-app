package com.imba.gymmemore.services;

import com.imba.gymmemore.DTO.AccountDetailsDTO;
import com.imba.gymmemore.DTO.ClassDetailsDTO;
import com.imba.gymmemore.DTO.GroupClassDTO;
import com.imba.gymmemore.models.*;
import com.imba.gymmemore.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClientAccountService {
    private final ProfLevelRepository profLevelRepository;
    private final PersonRepository personRepository;
    private final BankAccountRepository bankAccountRepository;
    private final MembershipRepository membershipRepository;
    private final GroupClassRepository groupClassRepository;
    private final ClassTypeRepository classTypeRepository;
    private final Client_GroupClassRepository clientGroupClassRepository;

    public ClientAccountService(ClassTypeRepository classTypeRepository, GroupClassRepository groupClassRepository, ProfLevelRepository profLevelRepository, PersonRepository personRepository, BankAccountRepository bankAccountRepository, MembershipRepository membershipRepository, Client_GroupClassRepository clientGroupClassRepository) {
        this.classTypeRepository = classTypeRepository;
        this.groupClassRepository = groupClassRepository;
        this.profLevelRepository = profLevelRepository;
        this.personRepository = personRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.membershipRepository = membershipRepository;
        this.clientGroupClassRepository = clientGroupClassRepository;
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


    public Map<String, GroupClass> getGroupClassesForWeek(LocalDate startDate, LocalDate endDate) {
        List<GroupClass> groupClasses = groupClassRepository.findAllByScheduledTimeBetween(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
        return groupClasses.stream()
                .collect(Collectors.toMap(
                        gc -> gc.getScheduledTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        gc -> gc
                ));
    }
    private String getDayHourKey(LocalDateTime scheduledTime) {
        String dayOfWeek = scheduledTime.getDayOfWeek().toString();
        String hour = scheduledTime.getHour() + ":00";
        return dayOfWeek + "_" + hour;
    }

    public ClassDetailsDTO getClassDetails(Long classId) throws IOException {
        GroupClass groupClass = groupClassRepository.findById(classId).orElseThrow(() -> new IOException("Class not found"));
        ClassType classType = classTypeRepository.findById(groupClass.getClassType().getId()).orElseThrow(() -> new IOException("Class type not found"));
        Coach coach = (Coach) personRepository.findById(groupClass.getCoach().getId()).orElseThrow(() -> new IOException("Coach not found"));
        return new ClassDetailsDTO(classId, coach.getFirstName(), coach.getBranch().getName(), groupClass.getPopularity(), groupClass.getRating());
    }
    @Transactional
    public boolean signUpForClass(Long classId, String username) {
        Client client = (Client)personRepository.findClientByUsername(username);
        if (client == null) {
            throw new RuntimeException("Client not found with username: " + username);
        }
        GroupClass groupClass = groupClassRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Group class not found with ID: " + classId));
        Client_GroupClass clientGroupClass = new Client_GroupClass(client, groupClass);
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
