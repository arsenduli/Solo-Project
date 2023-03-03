package com.betaplan.soloproject.soloprojectt.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min = 4)
    private String nameAdmin;
    @Email
    @Size(min = 4)
    private String emailAdmin;

    @NotEmpty
    @Size(min = 8)
    private String passwordAdmin;

    @Transient
    private String confirmPasswordAdmin;
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$")
    private String tel;

    @Pattern(regexp = "([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)")
    private String city;

    @Pattern(regexp = "([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)")
    private String country;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateAt;

    @PrePersist
    protected void onCreate() {
        this.createAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateAt = new Date();
    }

    @OneToMany(mappedBy = "adminProduct",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

    public Admin() {
    }

    public Admin(Long id, String nameAdmin, String emailAdmin, String passwordAdmin, String confirmPasswordAdmin, Date createAt, Date updateAt) {
        this.id = id;
        this.nameAdmin = nameAdmin;
        this.emailAdmin = emailAdmin;
        this.passwordAdmin = passwordAdmin;
        this.confirmPasswordAdmin = confirmPasswordAdmin;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAdmin() {
        return nameAdmin;
    }

    public void setNameAdmin(String nameAdmin) {
        this.nameAdmin = nameAdmin;
    }

    public String getEmailAdmin() {
        return emailAdmin;
    }

    public void setEmailAdmin(String emailAdmin) {
        this.emailAdmin = emailAdmin;
    }

    public String getPasswordAdmin() {
        return passwordAdmin;
    }

    public void setPasswordAdmin(String passwordAdmin) {
        this.passwordAdmin = passwordAdmin;
    }

    public String getConfirmPasswordAdmin() {
        return confirmPasswordAdmin;
    }

    public void setConfirmPasswordAdmin(String confirmPasswordAdmin) {
        this.confirmPasswordAdmin = confirmPasswordAdmin;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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
}
