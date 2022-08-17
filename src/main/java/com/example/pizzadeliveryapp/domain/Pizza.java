package com.example.pizzadeliveryapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer pizzaId;
    private String name;
    private double price;
    private int inventory;
    public boolean isOutOfStock(){
        return inventory==0;
    }
}
