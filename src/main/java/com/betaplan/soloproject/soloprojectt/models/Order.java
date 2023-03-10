package com.betaplan.soloproject.soloprojectt.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  Long id;

    @NotNull
    private  String address;
    @NotNull
    private String city;
    @NotNull
    private String country;
    @NotNull
    private Integer zipCode;
    @NotNull
    private Integer tel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userOrder;

    @OneToMany(mappedBy = "orderProduct" , cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Product> productsInOrder;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }


    public User getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(User userOrder) {
        this.userOrder = userOrder;
    }

    public List<Product> getProductsInOrder() {
        return productsInOrder;
    }

    public void setProductsInOrder(List<Product> productsInOrder) {
        this.productsInOrder = productsInOrder;
    }
}


