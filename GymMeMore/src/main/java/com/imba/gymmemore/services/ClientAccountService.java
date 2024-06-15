package com.imba.gymmemore.services;

import com.imba.gymmemore.DTO.*;
import com.imba.gymmemore.models.*;
import com.imba.gymmemore.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientAccountService {
    private final ProfLevelRepository profLevelRepository;
    private final PersonRepository personRepository;
    private final BankAccountRepository bankAccountRepository;
    private final MembershipRepository membershipRepository;
    private final GroupClassRepository groupClassRepository;
    private final ClassTypeRepository classTypeRepository;
    private final ReviewRepository reviewRepository;
    private final Client_GroupClassRepository clientGroupClassRepository;
    private final IndividualSessionRepository individualSessionRepository;

    public ClientAccountService(ClassTypeRepository classTypeRepository, GroupClassRepository groupClassRepository, ProfLevelRepository profLevelRepository, PersonRepository personRepository, BankAccountRepository bankAccountRepository, MembershipRepository membershipRepository, ReviewRepository reviewRepository, Client_GroupClassRepository clientGroupClassRepository, IndividualSessionRepository individualSessionRepository) {
        this.classTypeRepository = classTypeRepository;
        this.groupClassRepository = groupClassRepository;
        this.profLevelRepository = profLevelRepository;
        this.personRepository = personRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.membershipRepository = membershipRepository;
        this.reviewRepository = reviewRepository;
        this.clientGroupClassRepository = clientGroupClassRepository;
        this.individualSessionRepository = individualSessionRepository;
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

    public List<GroupClassDTO> getFutureClasses() {
        LocalDateTime now = LocalDateTime.now();
        return groupClassRepository.findByScheduledTimeAfter(now)
                .stream()
                .map(c -> {
            GroupClassDTO dto = new GroupClassDTO();
            dto.setId(c.getId());
            dto.setScheduledTime(c.getScheduledTime().toString());
            dto.setClassName(c.getClassType().getName());
            return dto;
        })
                .collect(Collectors.toList());
    }

    public List<GroupClassDTO> getFutureClassesForClient(Long clientId) {
        LocalDateTime now = LocalDateTime.now();
        return clientGroupClassRepository.findByClientIdAndGroupClass_ScheduledTimeAfter(clientId, now)
                .stream()
                .map(c -> {
            GroupClassDTO dto = new GroupClassDTO();
            dto.setId(c.getId());
            dto.setScheduledTime(c.getGroupClass().getScheduledTime().toString());
            dto.setClassName(c.getGroupClass().getClassType().getName());
            return dto;
        })
                .collect(Collectors.toList());
    }

    public List<ReviewDTO> getPastClassesForReview(Long clientId) {
        LocalDateTime now = LocalDateTime.now();
        return clientGroupClassRepository.findByClientIdAndGroupClass_ScheduledTimeBefore(clientId, now)
                .stream()
                .filter(cgc -> cgc.getReview() == null)
                .map(cgc -> {
                    ReviewDTO dto = new ReviewDTO();
                    dto.setClassId(cgc.getGroupClass().getId());
                    dto.setClassName(cgc.getGroupClass().getClassType().getName());
                    dto.setScheduledDate(cgc.getGroupClass().getScheduledTime().toString());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean submitReview(Long clientId, Long classId, int rating, String feedback) throws IOException {
        Review review = new Review();
        review.setRating(rating);
        review.setFeedback(feedback);
        reviewRepository.save(review);

        Client_GroupClass cgc = clientGroupClassRepository.findByClientIdAndGroupClassId(clientId, classId);
        if (cgc == null) {
            IndividualSession ind = individualSessionRepository.findById(classId)
                    .orElseThrow(() -> new IOException("Individual Session not found"));
            ind.setReview(review);
            individualSessionRepository.save(ind);
        } else {
            cgc.setReview(review);
            clientGroupClassRepository.save(cgc);
        }
        Client client = (Client) personRepository.findById(clientId)
                .orElseThrow(() -> new IOException("Client not found"));
        client.setClassesCount(client.getClassesCount() + 1);
        personRepository.save(client);
        return true;
    }

    public List<Coach> getAvailableCoaches(LocalDateTime startTime, LocalDateTime endTime) {
        List<Long> occupiedCoaches = individualSessionRepository.findOccupiedCoaches(startTime, endTime);
        occupiedCoaches.addAll(groupClassRepository.findOccupiedCoaches(startTime, endTime));

        return personRepository.findAll().stream()
                .filter(person -> person instanceof Coach)
                .map(person -> (Coach) person)
                .filter(coach -> !occupiedCoaches.contains(coach.getId()))
                .collect(Collectors.toList());
    }

    public boolean scheduleIndividualSession(Long clientId, Long coachId, LocalDateTime scheduledTime) {
        IndividualSession session = new IndividualSession();
        session.setClient((Client) personRepository.findClientById(clientId));
        session.setCoach((Coach)personRepository.findClientById(coachId));
        session.setScheduledTime(scheduledTime);
        individualSessionRepository.save(session);
        return true;
    }
    public ClientDTO getClientById(Long id){
        Client cl = (Client)personRepository.findClientById(id);
        return new ClientDTO(cl);
    }
    public ClassDetailsDTO getClassDetails(Long classId) throws IOException {
        Optional<GroupClass> groupClassOpt = groupClassRepository.findById(classId);
        if (groupClassOpt.isPresent()) {
            GroupClass groupClass = groupClassOpt.get();
            ClassDetailsDTO classDetails = new ClassDetailsDTO();
            classDetails.setId(groupClass.getId());
            classDetails.setCoachFirstName(groupClass.getCoach().getFirstName());
            classDetails.setBranchName(groupClass.getCoach().getBranch().getDescription());
            classDetails.setClassDescription(groupClass.getClassType().getDescription());
            classDetails.setClassRating(groupClass.getRating());
            classDetails.setClassPopularity(groupClass.getPopularity());
            classDetails.setCoachRating(groupClass.getCoach().getRating());
            return classDetails;
        } else {
            throw new IOException("Class not found");
        }
    }

    @Transactional
    public boolean signUpForClass(Long clientId, Long classId) throws IOException {
        int capacity = clientGroupClassRepository.countByGroupClassId(classId);
        GroupClass gc = groupClassRepository.findById(classId).orElseThrow(() -> new IOException("Class not found"));
        if (capacity < gc.getClassType().getCapacity()) {
            Client_GroupClass clientGroupClass = new Client_GroupClass();
            clientGroupClass.setClient((Client) personRepository.findClientById(clientId));
            clientGroupClass.setGroupClass(groupClassRepository.findGroupClassById(classId));
            clientGroupClassRepository.save(clientGroupClass);
            return true;
        } else {
            return false;
        }
    }
}
