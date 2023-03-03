package com.betaplan.soloproject.soloprojectt.repositories;

import com.betaplan.soloproject.soloprojectt.models.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepo extends CrudRepository<Admin,Long> {
    List<Admin> findAll();
    Optional<Admin> findByEmailAdmin(String email);
}
