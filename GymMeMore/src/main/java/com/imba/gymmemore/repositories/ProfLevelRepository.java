package com.imba.gymmemore.repositories;

import com.imba.gymmemore.models.ProfLevel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProfLevelRepository extends CrudRepository<ProfLevel, Long> {
    ProfLevel findProfLevelByName(String name);
    @Query(value = "SELECT p.* FROM PROF_LEVEL p JOIN PERSON per ON p.ID = per.PROF_LEVEL_ID WHERE per.ID = :id", nativeQuery = true)
    ProfLevel findProfLevelByClientId(Long id);

}
