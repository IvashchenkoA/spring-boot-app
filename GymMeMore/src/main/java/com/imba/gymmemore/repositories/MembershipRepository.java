package com.imba.gymmemore.repositories;

import com.imba.gymmemore.models.Membership;
import org.springframework.data.repository.CrudRepository;

public interface MembershipRepository extends CrudRepository<Membership, Long> {
    Membership findMembershipByClientId(Long id);
}
