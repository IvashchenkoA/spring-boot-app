package com.imba.gymmemore.services;

import com.imba.gymmemore.DTO.CoachDetailsDTO;
import com.imba.gymmemore.models.Coach;
import com.imba.gymmemore.repositories.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class CoachService {
    private final PersonRepository personRepository;

    public CoachService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public CoachDetailsDTO getCoachDetails(Long id){
        Coach coach = (Coach)personRepository.findClientById(id);
        CoachDetailsDTO coachDetailsDTO = new CoachDetailsDTO();
        coachDetailsDTO.setName(coach.getFirstName());
        coachDetailsDTO.setHireDate(coachDetailsDTO.getHireDate().toString());
        coachDetailsDTO.setBranch(coach.getBranch().getName());
        coachDetailsDTO.setRating(coach.getRating());
        coachDetailsDTO.setConductedClasses(coach.getConductedClasses());
        return coachDetailsDTO;
    }
}
