package com.example.pizzadeliveryapp.service;

import com.example.pizzadeliveryapp.domain.Order;
import com.example.pizzadeliveryapp.domain.OrderLine;
import com.example.pizzadeliveryapp.domain.Pizza;
import com.example.pizzadeliveryapp.repository.OrderRepo;
import com.example.pizzadeliveryapp.repository.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepo orderRepo;

    @Autowired
    PizzaService pizzaService;
    public List<Order> getAllOrders(){
        return orderRepo.findAll();
    }
    public Order getOrderById(int orderId){
        return orderRepo.findById(orderId).get();
    }
    public void addOrder(Order order) {
        List<OrderLine> items = order.getOrderLineItem();
        for (int i = 0; i < items.size(); i++) {
            int pizzaId = items.get(i).getPizza().getPizzaId();
            Pizza pizza = pizzaService.getPizzaById(pizzaId);
            if (pizza.isOutOfStock()) {
                System.out.print("Pizza is out of stock");
            } else {
                pizza.setInventory(items.get(i).amount);
                pizzaService.updatePizzaById(pizzaId, pizza);
            }
        }
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
