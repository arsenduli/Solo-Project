package com.betaplan.soloproject.soloprojectt.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 4)
    private String name;
    @Min(1)
    private Double price;

    @NotNull
    @Size(min = 8)
    private String description;

    private String url;

    @Column(updatable=false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt= new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin adminProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order orderProduct;

    @OneToMany(mappedBy = "productReviews",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reviews> reviews;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "categories_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Categories> categories;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cart" , joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn( name = "user_id"))
    private List<User> userCart;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "wishlist" , joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn( name = "user_id"))
    private List<User> userWishlist;


    public Product() {
    }

    public Product(String name, Double price, String description, String url, Admin adminProduct) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.url = url;
        this.adminProduct = adminProduct;
    }

    public Product(String url, Admin adminProduct) {
        this.url = url;
        this.adminProduct = adminProduct;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Admin getAdminProduct() {
        return adminProduct;
    }

    public void setAdminProduct(Admin adminProduct) {
        this.adminProduct = adminProduct;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }


    public List<User> getUserCart() {
        return userCart;
    }

    public void setUserCart(List<User> userCart) {
        this.userCart = userCart;
    }

    public List<User> getUserWishlist() {
        return userWishlist;
    }

    public void setUserWishlist(List<User> userWishlist) {
        this.userWishlist = userWishlist;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Order getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(Order orderProduct) {
        this.orderProduct = orderProduct;
    }

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }
    public Double getAvarageRating(){
        List<Reviews> tempRating = this.getReviews();
        double sum = 0;
        for (Reviews reviews1:tempRating){
            if (reviews1.getRate() != null){
                sum += reviews1.getRate();
            }
        }
        return sum/tempRating.size();
    }
}
