package com.betaplan.soloproject.soloprojectt.repositories;


import com.betaplan.soloproject.soloprojectt.models.Categories;
import com.betaplan.soloproject.soloprojectt.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends CrudRepository<Categories, Long> {

    List<Categories> findAll();
    List<Categories> findAllByProducts(Product product);
    List<Categories> findByProductsNotContains(Product product);
}
