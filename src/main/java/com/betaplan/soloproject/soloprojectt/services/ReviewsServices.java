package com.betaplan.soloproject.soloprojectt.services;


import com.betaplan.soloproject.soloprojectt.models.Product;
import com.betaplan.soloproject.soloprojectt.models.Reviews;
import com.betaplan.soloproject.soloprojectt.models.User;
import com.betaplan.soloproject.soloprojectt.repositories.ReviewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewsServices {

    @Autowired
    private ReviewsRepo reviewsRepo;

    public ReviewsServices(ReviewsRepo reviewsRepo) {
        this.reviewsRepo = reviewsRepo;
    }

    // find all

    public List<Reviews> getAllRevies(){
        return reviewsRepo.findAll();
    }

    public Reviews getByIdReviews(Long id){
        Optional<Reviews> optional = reviewsRepo.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    // find by user
    public List<Reviews> getByUser(User user){
        return reviewsRepo.findByUserReview(user);
    }
    // find by product
    public List<Reviews> getByProduct(Product product) {
        return reviewsRepo.findByProductReviews(product);
    }

        // create review
    public Reviews createRevi(Reviews reviews){
        return reviewsRepo.save(reviews);
    }
    // delete revi
    public void deleteRevi(Long id){
        reviewsRepo.deleteById(id);
    }
}
