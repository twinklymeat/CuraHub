package com.curahub.curahub.reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/reviews")
public class ReviewsController {
    @Autowired
    private ReviewsService reviewsService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getReviewsByDoctor(@PathVariable long id) {
        return ResponseEntity.ok(reviewsService.getReviewsByDoctor(id));
    }

    @PostMapping
    public ResponseEntity<Object> createReview(@RequestBody Reviews review) {
        return ResponseEntity.ok(reviewsService.addReview(review));
    }

    @PostMapping("/doctor/add")
    public Object addReview(Reviews review) {
        Reviews rev = reviewsService.getReviewsByID(review.getID());
        rev.setResponse(review.getResponse());
        reviewsService.addReview(rev);
        return "redirect:/doctor/dashboard/"+rev.getDoctor().getID();
    }

    @GetMapping("/doctor/delete")
    public Object deleteResponse(@RequestParam long ID) {
        Reviews review = reviewsService.getReviewsByID(ID);
        // reviewsService.deleteById(ID);
        review.setResponse(null);
        reviewsService.addReview(review);
        
        return "redirect:/doctor/dashboard/"+review.getDoctor().getID();
    }
}
