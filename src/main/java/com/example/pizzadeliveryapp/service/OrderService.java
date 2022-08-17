package com.example.pizzadeliveryapp.service;

import com.example.pizzadeliveryapp.domain.Order;
import com.example.pizzadeliveryapp.domain.OrderLine;
import com.example.pizzadeliveryapp.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepo orderRepo;

    public List<Order> getAllOrders(){
        return orderRepo.findAll();
    }
    public Order getOrderById(int orderId){
        return orderRepo.findById(orderId).get();
    }
    public void addOrder(Order order){
        orderRepo.save(order);
    }
    public Order updateOrderById(int orderId, List<OrderLine> orderLineItem){
        Order toBeUpdated = orderRepo.findById(orderId).get();
        toBeUpdated.setOrderLineItem(orderLineItem);
        return toBeUpdated;
    }
    public void deleteOrderById(int pizzaId) {
        Order toBeUpdated = orderRepo.findById(pizzaId).get();
        orderRepo.delete(toBeUpdated);
    }
}
