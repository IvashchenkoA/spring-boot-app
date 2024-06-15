package com.imba.gymmemore.repositories;

import com.imba.gymmemore.models.GroupClass;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface GroupClassRepository extends CrudRepository<GroupClass, Long> {
    List<GroupClass> findAllByScheduledTimeBetween(LocalDateTime start, LocalDateTime end);
}
