package com.betaplan.soloproject.soloprojectt.services;


import com.betaplan.soloproject.soloprojectt.models.Admin;
import com.betaplan.soloproject.soloprojectt.models.Categories;
import com.betaplan.soloproject.soloprojectt.models.Order;
import com.betaplan.soloproject.soloprojectt.models.Product;
import com.betaplan.soloproject.soloprojectt.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {

    @Autowired
    private ProductRepo productRepo;

    

    public   List<Product> getByadminAndCate(Admin admin , Categories categories){
        return productRepo.findByAdminProductAndCategories(admin , categories);
    }

    public  List<Product> getNotContaingAdminPro(Admin admin , Categories categories){
        return productRepo.findByAdminProductAndCategoriesNotContaining(admin , categories);
    }

    public List<Product> bestSellin(Order order){
        return productRepo.findProductByOrderProduct(order);
    }
    public List<Product> adminProduct(Admin admin){
        return productRepo.findAllByAdminProduct(admin);
    }

    public ProductServices(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    //find all
    public List<Product> getAllProduct(){
        return productRepo.findAll();
    }
    // find by id
    public Product getByIdProduct(Long id){
        Optional<Product> optional = productRepo.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    // find by category
    public List<Product> getByCategory(Categories category){
        return productRepo.findAllByCategories(category);
    }
    // find by not container
    public List<Product> getByNotContainer(Categories category){
        return productRepo.findByCategoriesNotContaining(category);
    }
    //create
    public Product createProduct(Product product){
        return productRepo.save(product);
    }
    //update
    public Product updateProduct(Product product){
        return productRepo.save(product);
    }
    //delete
    public void deleteProduct(Long id){
        productRepo.deleteById(id);
    }
    public List<Product> search(String name){
        return productRepo.findProductByName(name);
    }

    public List<Product> getNew(){
        return productRepo.findTop20ByOrderByCreatedAtDesc();
    }


}
