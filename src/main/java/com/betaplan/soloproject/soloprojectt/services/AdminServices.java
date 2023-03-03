package com.betaplan.soloproject.soloprojectt.services;


import com.betaplan.soloproject.soloprojectt.models.Admin;
import com.betaplan.soloproject.soloprojectt.models.LoginAdmin;
import com.betaplan.soloproject.soloprojectt.repositories.AdminRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServices {

    @Autowired
    private AdminRepo adminRepo;

    public AdminServices(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }


    //find all
    public List<Admin> findAdmin(){
        return adminRepo.findAll();
    }
    //find by id
    public Admin findAdminId(Long id){
        Optional<Admin> optional = adminRepo.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public Optional<Admin> findbyEmailAdmin(String email){
        return adminRepo.findByEmailAdmin(email);
    }
    //create
    public Admin createAdmin(Admin admin){
        return adminRepo.save(admin);
    }
    //update
    public Admin updateAdmin(Admin admin){
        return adminRepo.save(admin);
    }
    //delete
    public void deleteAdminId(Long id){
        adminRepo.deleteById(id);
    }

    public Admin registerAdmin(Admin admin , BindingResult result){
        Optional<Admin> optional = adminRepo.findByEmailAdmin(admin.getEmailAdmin());
        if (optional.isPresent()){
            result.rejectValue("emailAdmin" , "Match" , "This email exist!");
        }
        if (!admin.getPasswordAdmin().equals(admin.getConfirmPasswordAdmin())){
            result.rejectValue("passwordAdmin", "Match", "Type correct password!");
        }
        if (result.hasErrors()){
            return null;
        }
        String hash = BCrypt.hashpw(admin.getPasswordAdmin() , BCrypt.gensalt());
        admin.setPasswordAdmin(hash);
        return createAdmin(admin);
    }
    public Admin loginAdmin(LoginAdmin loginAdmin, BindingResult result){
        Optional<Admin> optional = adminRepo.findByEmailAdmin(loginAdmin.getEmailAdmin());
        if (!optional.isPresent()){
            result.rejectValue("emailAdmin" , "Match" , "This email dont exist!");
            return null;
        }
        Admin admin = optional.get();
        if (!BCrypt.checkpw(loginAdmin.getPasswordAdmin(), admin.getPasswordAdmin())){
            result.rejectValue("passwordAdmin", "Match", "Type correct password!");
            return null;
        }
        return admin;
    }
}
