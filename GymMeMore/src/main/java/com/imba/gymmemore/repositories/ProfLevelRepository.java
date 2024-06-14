package com.imba.gymmemore.repositories;

import com.imba.gymmemore.models.ProfLevel;
import org.springframework.data.repository.CrudRepository;

public interface ProfLevelRepository extends CrudRepository<ProfLevel, Long> {
    ProfLevel findProfLevelByName(String name);
}
