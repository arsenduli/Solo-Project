package com.betaplan.soloproject.soloprojectt.services;


import com.betaplan.soloproject.soloprojectt.models.Categories;
import com.betaplan.soloproject.soloprojectt.models.Product;
import com.betaplan.soloproject.soloprojectt.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServices {

    @Autowired
    private CategoryRepo categoryRepo;


    public CategoryServices(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }


    //find all
    public List<Categories> getall() {
        return categoryRepo.findAll();
    }

    public void uploadPicCategoris(String name , String urlCategoris){
        Categories newCategories = new Categories(name , urlCategoris);
        this.categoryRepo.save(newCategories);
    }

    // find by id
    public Categories getById(Long id) {
        Optional<Categories> optional = categoryRepo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;

    }

    //find by product
    public List<Categories> getByProduct(Product product) {
        return categoryRepo.findAllByProducts(product);
    }

    //find by produc not container
    public List<Categories> getByProNotCon(Product product) {
        return categoryRepo.findByProductsNotContains(product);
    }

    // create
    public Categories createCategory(Categories category) {
        return categoryRepo.save(category);
    }

    //update
    public Categories updateCategory(Categories category) {
        return categoryRepo.save(category);
    }

    //delete
    public void deleteCategorY(Long id) {

    }
}
