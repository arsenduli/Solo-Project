package com.betaplan.soloproject.soloprojectt.repositories;


import com.betaplan.soloproject.soloprojectt.models.Admin;
import com.betaplan.soloproject.soloprojectt.models.Categories;
import com.betaplan.soloproject.soloprojectt.models.Order;
import com.betaplan.soloproject.soloprojectt.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {

    List<Product> findAll();
    List<Product> findAllByCategories(Categories categories);
    List<Product> findByAdminProductAndCategories(Admin admin , Categories categories);
    List<Product> findByAdminProductAndCategoriesNotContaining(Admin admin , Categories categories);
    List<Product> findByCategoriesNotContaining(Categories categories);
    List<Product> findProductByName(String name);
    List<Product> findTop20ByOrderByCreatedAtDesc();
    List<Product> findAllByAdminProduct(Admin admin);

    List<Product> findProductByOrderProduct(Order order);
}
