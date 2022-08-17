package com.example.pizzadeliveryapp.service;

import com.example.pizzadeliveryapp.domain.Pizza;
import com.example.pizzadeliveryapp.repository.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    @Autowired
    PizzaRepo pizzaRepo;

    public List<Pizza> getAllPizzas(){
        return pizzaRepo.findAll();
    }
    public Pizza getPizzaById(int pizzaId){
        return pizzaRepo.findById(pizzaId).get();
    }
    public void addPizza(Pizza pizza){
        pizzaRepo.save(pizza);
    }
    public Pizza updatePizzaById(int pizzaId, Pizza pizza){
        Pizza toBeUpdated = pizzaRepo.findById(pizzaId).get();
        toBeUpdated.setName(pizza.getName());
        toBeUpdated.setInventory(pizza.getInventory());
        toBeUpdated.setPrice(pizza.getPrice());
        return toBeUpdated;
    }
    public void deletePizzaById(int pizzaId) {
        Pizza toBeUpdated = pizzaRepo.findById(pizzaId).get();
        pizzaRepo.delete(toBeUpdated);
    }
}
