package com.betaplan.soloproject.soloprojectt.repositories;


import com.betaplan.soloproject.soloprojectt.models.Order;
import com.betaplan.soloproject.soloprojectt.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {
    List<Order> findAll();
    List<Order> findByUserOrder(User user);

}
