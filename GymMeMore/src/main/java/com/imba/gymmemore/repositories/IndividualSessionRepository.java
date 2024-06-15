package com.imba.gymmemore.repositories;

import com.imba.gymmemore.models.IndividualSession;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IndividualSessionRepository extends CrudRepository<IndividualSession, Long> {
    @Query("SELECT i.coach.Id FROM IndividualSession i WHERE (i.scheduledTime BETWEEN :startTime AND :endTime)")
    List<Long> findOccupiedCoaches(LocalDateTime startTime, LocalDateTime endTime);
}
