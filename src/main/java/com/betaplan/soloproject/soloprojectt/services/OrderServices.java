package com.betaplan.soloproject.soloprojectt.services;

import com.betaplan.soloproject.soloprojectt.models.Order;
import com.betaplan.soloproject.soloprojectt.models.User;
import com.betaplan.soloproject.soloprojectt.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServices {

    @Autowired
    private OrderRepo orderRepo;


    public OrderServices(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }


    // find all

    public List<Order> getAllOrders(){
        return orderRepo.findAll();
    }
    public List<Order> getOrderByUser(User user){
        return orderRepo.findByUserOrder(user);
    }


    public Order createOrder(Order order){
        return orderRepo.save(order);
    }
}

