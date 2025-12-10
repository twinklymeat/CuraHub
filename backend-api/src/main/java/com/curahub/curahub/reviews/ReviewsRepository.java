package com.curahub.curahub.reviews;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
    List<Reviews> getReviewsByDoctor_ID(long id);
    Reviews getReviewsByID(long id);
}