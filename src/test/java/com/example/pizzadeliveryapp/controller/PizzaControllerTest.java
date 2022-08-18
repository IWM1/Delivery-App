package com.example.pizzadeliveryapp.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.pizzadeliveryapp.domain.Pizza;
import com.example.pizzadeliveryapp.service.PizzaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PizzaController.class})
@ExtendWith(SpringExtension.class)
class PizzaControllerTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private PizzaController pizzaController;

    @MockBean
    private PizzaService pizzaService;

    /**
     * Method under test: {@link PizzaController#addPizza(Pizza)}
     */
    @Test
    void testAddPizza() throws Exception {
        doNothing().when(pizzaService).addPizza((Pizza) any());

        Pizza pizza = new Pizza();
        pizza.setInventory(1);
        pizza.setName("Name");
        pizza.setPizzaId(123);
        pizza.setPrice(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(pizza);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/pizza/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(pizzaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"pizzaId\":123,\"name\":\"Name\",\"price\":10.0,\"inventory\":1,\"outOfStock\":false}"));
    }

    /**
     * Method under test: {@link PizzaController#getPizzaById(int)}
     */
    @Test
    void testGetPizzaById() throws Exception {
        Pizza pizza = new Pizza();
        pizza.setInventory(1);
        pizza.setName("Name");
        pizza.setPizzaId(123);
        pizza.setPrice(10.0d);
        when(pizzaService.getPizzaById(anyInt())).thenReturn(pizza);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/pizza/read/{pizzaId}", 123);
        MockMvcBuilders.standaloneSetup(pizzaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"pizzaId\":123,\"name\":\"Name\",\"price\":10.0,\"inventory\":1,\"outOfStock\":false}"));
    }

    /**
     * Method under test: {@link PizzaController#getAllPizza()}
     */
    @Test
    void testGetAllPizza() throws Exception {
        when(pizzaService.getAllPizzas()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/pizza/read");
        MockMvcBuilders.standaloneSetup(pizzaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PizzaController#getAllPizza()}
     */
    @Test
    void testGetAllPizza2() throws Exception {
        when(pizzaService.getAllPizzas()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/pizza/read");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(pizzaController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PizzaController#updatePizza(int, Pizza)}
     */
    @Test
    void testUpdatePizza() throws Exception {
        Pizza pizza = new Pizza();
        pizza.setInventory(1);
        pizza.setName("Name");
        pizza.setPizzaId(123);
        pizza.setPrice(10.0d);
        when(pizzaService.updatePizzaById(anyInt(), (Pizza) any())).thenReturn(pizza);

        Pizza pizza1 = new Pizza();
        pizza1.setInventory(1);
        pizza1.setName("Name");
        pizza1.setPizzaId(123);
        pizza1.setPrice(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(pizza1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/pizza/update/{pizzaId}", 123)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(pizzaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"pizzaId\":123,\"name\":\"Name\",\"price\":10.0,\"inventory\":1,\"outOfStock\":false}"));
    }
}

