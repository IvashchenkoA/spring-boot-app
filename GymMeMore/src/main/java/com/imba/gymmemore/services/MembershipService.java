package com.imba.gymmemore.services;

import com.imba.gymmemore.DTO.MembershipTypeDTO;
import com.imba.gymmemore.models.MembershipType;
import com.imba.gymmemore.repositories.MembershipTypeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class MembershipService {
    private final MembershipTypeRepository membershipTypeRepository;

    public MembershipService(MembershipTypeRepository membershipTypeRepository) {
        this.membershipTypeRepository = membershipTypeRepository;
    }
    public List<MembershipTypeDTO> getAllMembershipTypes(){
        List<MembershipTypeDTO> list = new ArrayList<>();
        for(MembershipType m : membershipTypeRepository.findAll()){
            MembershipTypeDTO dto = new MembershipTypeDTO(m.getName(),m.getDescription(),m.getPrice());
            list.add(dto);
        }
        return list;
    }
}
