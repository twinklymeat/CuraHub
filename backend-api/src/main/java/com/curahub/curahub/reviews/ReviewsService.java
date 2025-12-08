package com.curahub.curahub.reviews;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewsService {
    @Autowired
    private ReviewsRepository reviewsRepository;

    public List<Reviews> getReviewsByDoctor(long id) {
        return reviewsRepository.getReviewsByDoctor_ID(id);
    }

    public Reviews addReview(Reviews review) {
        return reviewsRepository.save(review);
    }

    public Reviews getReviewsByID(long id) {
        return reviewsRepository.getReviewsByID(id);
    }

    public void deleteResponse(long id) {
        Reviews rev = getReviewsByID(id);
        rev.setResponse(null);
    }

    public void deleteById(long id) {
        reviewsRepository.deleteById(id);
    }

}
