package com.betaplan.soloproject.soloprojectt.repositories;


import com.betaplan.soloproject.soloprojectt.models.Product;
import com.betaplan.soloproject.soloprojectt.models.Reviews;
import com.betaplan.soloproject.soloprojectt.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepo extends CrudRepository<Reviews, Long> {
    List<Reviews> findAll();
    List<Reviews> findByUserReview(User user);
    List<Reviews> findByProductReviews(Product product);
}
