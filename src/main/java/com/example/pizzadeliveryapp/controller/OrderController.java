package com.example.pizzadeliveryapp.controller;

import com.example.pizzadeliveryapp.domain.Order;
import com.example.pizzadeliveryapp.domain.OrderLine;
import com.example.pizzadeliveryapp.domain.Pizza;
import com.example.pizzadeliveryapp.service.OrderService;
import com.example.pizzadeliveryapp.service.PizzaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    Logger logger =  LoggerFactory.getLogger(Order.class);

    @PostMapping("/create")
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        orderService.addOrder(order);
        logger.trace("Accessing addOrder method");
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
    @GetMapping("/read")
    public ResponseEntity<?> getAllOrders(){
        List<Order> orders = orderService.getAllOrders();
        logger.trace("Accessing getAllOrders method");
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @GetMapping("/read/{orderId}")
    public ResponseEntity<Order> getPizzaById(@PathVariable int orderId){
        Order order = orderService.getOrderById(orderId);
        logger.trace("Accessing getPizzaById method");
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
    @PutMapping("/update/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable int orderId, @RequestBody List<OrderLine> orderLine) {
        Order updatedOrder = orderService.updateOrderById(orderId, orderLine);
        logger.trace("Accessing updateOrder method");
        return new ResponseEntity<Order>(updatedOrder, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<?> deleteOrder(int orderId){
        orderService.deleteOrderById(orderId);
        logger.trace("Accessing deleteOrder method");
        return new ResponseEntity<>( HttpStatus.OK);
    }
}