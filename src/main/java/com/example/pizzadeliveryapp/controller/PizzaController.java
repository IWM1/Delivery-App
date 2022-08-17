package com.example.pizzadeliveryapp.controller;

import com.example.pizzadeliveryapp.domain.Pizza;
import com.example.pizzadeliveryapp.service.PizzaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizza")
public class PizzaController {
    @Autowired
    PizzaService pizzaService;

    @Autowired
    private AuthenticationManager authenticationManager;

     Logger logger =  LoggerFactory.getLogger(Pizza.class);

    @PostMapping("/create")
    public ResponseEntity<Pizza> addPizza(@RequestBody Pizza pizza){
        pizzaService.addPizza(pizza);
        logger.trace("Accessing addPizza method");
        return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
    }
    @GetMapping("/read")
    public ResponseEntity<?> getAllPizza(){
        List<Pizza> pizzas = pizzaService.getAllPizzas();
        logger.trace("Accessing getAllPizza method");
        return new ResponseEntity<>(pizzas, HttpStatus.OK);
    }
    @GetMapping("/read/{pizzaId}")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable int pizzaId){
        Pizza pizza = pizzaService.getPizzaById(pizzaId);
        logger.trace("Accessing getPizzaById method");
        return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
    }
    @PutMapping("/update/{pizzaId}")
    public ResponseEntity<Pizza> updatePizza(@PathVariable int pizzaId, @RequestBody Pizza pizza) {
        pizzaService.updatePizzaById(pizzaId, pizza);
        logger.trace("Accessing updatePizza method");
        return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{pizzaId}")
    public ResponseEntity<?> deletePizzaById(int pizzaId){
        pizzaService.deletePizzaById(pizzaId);
        logger.trace("Accessing deletePizzaById method");
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
