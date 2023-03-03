package com.betaplan.soloproject.soloprojectt.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min = 4)
    private String userName;
    @Email
    @Size(min = 4)
    private String email;

    @NotEmpty
    @Size(min = 8)
    private String password;

    @Transient
    private String confirmPassword;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateAt;

    @PrePersist
    protected void onCreate(){
        this.createAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updateAt = new Date();
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cart" , joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn( name = "product_id"))
    private List<Product> addCart;

    @OneToMany(mappedBy = "userOrder" , fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    private List<Order> orders;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "wishlist" , joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn( name = "product_id"))
    private List<Product> addWishlist;

    @OneToMany(mappedBy = "userReview",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reviews> reviewsByUser;

    public User() {
    }

    public User(Long id, String userName, String email, String password, String confirmPassword, Date createAt, Date updateAt) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public List<Product> getAddCart() {
        return addCart;
    }

    public void setAddCart(List<Product> addCart) {
        this.addCart = addCart;
    }


    public List<Product> getAddWishlist() {
        return addWishlist;
    }

    public void setAddWishlist(List<Product> addWishlist) {
        this.addWishlist = addWishlist;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Reviews> getReviewsByUser() {
        return reviewsByUser;
    }

    public void setReviewsByUser(List<Reviews> reviewsByUser) {
        this.reviewsByUser = reviewsByUser;
    }

    public Double getTotal(){
    List<Product> ott = this.addCart;
        double sum = 0;
        for (Product product: ott){
            if (product.getPrice() != 0){
                sum+=product.getPrice();
            }
        }

        return sum;
    }
}
