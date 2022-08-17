package com.example.pizzadeliveryapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int orderId;
    public String customerName;
    @OneToMany
    public List<OrderLine> orderLineItem;

    public double calculatePrice(){
        double totalPrice = 0.0;
        for(int i = 0; i<orderLineItem.size(); i++){
            totalPrice += orderLineItem.get(i).getPizza().getPrice();
        }
        return totalPrice;
    }
}
