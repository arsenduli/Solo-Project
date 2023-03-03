package com.betaplan.soloproject.soloprojectt.repositories;

import com.betaplan.soloproject.soloprojectt.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    List<User> findAll();
    Optional<User> findByEmail(String email);
}
