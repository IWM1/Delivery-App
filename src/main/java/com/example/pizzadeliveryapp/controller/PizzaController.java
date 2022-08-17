package com.example.pizzadeliveryapp.controller;

import com.example.pizzadeliveryapp.domain.JwtRequest;
import com.example.pizzadeliveryapp.domain.JwtResponse;
import com.example.pizzadeliveryapp.domain.Pizza;
import com.example.pizzadeliveryapp.service.PizzaService;
import com.example.pizzadeliveryapp.service.UserService;
import com.example.pizzadeliveryapp.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizza")
public class PizzaController {
    @Autowired
    PizzaService pizzaService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/create")
    public ResponseEntity<Pizza> addPizza(@RequestBody Pizza pizza){
        pizzaService.addPizza(pizza);
        return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
    }
    @GetMapping("/read")
    public ResponseEntity<?> getAllPizza(){
        List<Pizza> pizzas = pizzaService.getAllPizzas();
        return new ResponseEntity<>(pizzas, HttpStatus.OK);
    }
    @GetMapping("/read/{pizzaId}")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable int pizzaId){
        Pizza pizza = pizzaService.getPizzaById(pizzaId);
        return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
    }
    @PutMapping("/update/{pizzaId}")
    public ResponseEntity<Pizza> addPizza(@PathVariable int pizzaId, @RequestBody Pizza pizza) {
        pizzaService.updatePizzaById(pizzaId, pizza);
        return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{pizzaId}")
    public ResponseEntity<?> deletePizza(int pizzaId){
        pizzaService.deletePizzaById(pizzaId);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
