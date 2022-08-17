package com.example.pizzadeliveryapp.repository;

import com.example.pizzadeliveryapp.domain.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepo extends JpaRepository<Pizza, Integer> {
}

