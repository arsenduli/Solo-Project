package com.betaplan.soloproject.soloprojectt.services;


import com.betaplan.soloproject.soloprojectt.models.LoginUser;
import com.betaplan.soloproject.soloprojectt.models.User;
import com.betaplan.soloproject.soloprojectt.repositories.UserRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepo userRepo;

    public UserServices(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    //find all
    public List<User> findUser(){
        return userRepo.findAll();
    }
    //find by id
    public User findUserId(Long id){
        Optional<User> optional = userRepo.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public Optional<User> findbyEmail(String email){
        return userRepo.findByEmail(email);
    }
    //create
    public User createUser(User user){
        return userRepo.save(user);
    }
    //update
    public User updateUser(User user){
        return userRepo.save(user);
    }
    //delete
    public void deleteUserId(Long id){
        userRepo.deleteById(id);
    }

    public User register(User user , BindingResult result){
        Optional<User> optional = userRepo.findByEmail(user.getEmail());
        if (optional.isPresent()){
            result.rejectValue("email" , "Match" , "This email exist!");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())){
            result.rejectValue("password", "Match", "Type correct password!");
        }
        if (result.hasErrors()){
            return null;
        }
        String hash = BCrypt.hashpw(user.getPassword() , BCrypt.gensalt());
        user.setPassword(hash);
        return createUser(user);
    }
    public User login(LoginUser loginUser, BindingResult result){
        Optional<User> optional = userRepo.findByEmail(loginUser.getEmail());
        if (!optional.isPresent()){
            result.rejectValue("email" , "Match" , "This email dont exist!");
            return null;
        }
        User user = optional.get();
        if (!BCrypt.checkpw(loginUser.getPassword(), user.getPassword())){
            result.rejectValue("password", "Match", "Type correct password!");
            return null;
        }
        return user;
    }
}
