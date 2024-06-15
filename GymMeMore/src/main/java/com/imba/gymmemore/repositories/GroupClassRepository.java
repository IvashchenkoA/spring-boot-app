package com.imba.gymmemore.repositories;

import com.imba.gymmemore.models.GroupClass;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface GroupClassRepository extends CrudRepository<GroupClass, Long> {
    List<GroupClass> findByScheduledTimeAfter(LocalDateTime time);
    GroupClass findGroupClassById(Long id);
    @Query("SELECT g.coach.Id FROM GroupClass g WHERE (g.scheduledTime BETWEEN :startTime AND :endTime)")
    List<Long> findOccupiedCoaches(LocalDateTime startTime, LocalDateTime endTime);
}
