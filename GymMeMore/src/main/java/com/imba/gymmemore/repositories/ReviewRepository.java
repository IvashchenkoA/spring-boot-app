package com.imba.gymmemore.repositories;

import com.imba.gymmemore.models.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
