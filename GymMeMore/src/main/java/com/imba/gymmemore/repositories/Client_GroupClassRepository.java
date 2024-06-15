package com.imba.gymmemore.repositories;

import com.imba.gymmemore.models.Client_GroupClass;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface Client_GroupClassRepository extends CrudRepository<Client_GroupClass, Long> {
    int countByGroupClassId(Long id);
    List<Client_GroupClass> findByClientIdAndGroupClass_ScheduledTimeBefore(Long id, LocalDateTime dt);
    List<Client_GroupClass> findByClientIdAndGroupClass_ScheduledTimeAfter(Long id, LocalDateTime dt);
    Client_GroupClass findByClientIdAndGroupClassId(Long clientId, Long groupClassId);
}
