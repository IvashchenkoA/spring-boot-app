package com.imba.gymmemore.repositories;

import com.imba.gymmemore.models.MembershipType;
import org.springframework.data.repository.CrudRepository;

public interface MembershipTypeRepository extends CrudRepository<MembershipType, Long> {
    MembershipType findMembershipTypeById(Long id);
}
