package com.joaodcpjunior.pizzas.dtos;

import com.joaodcpjunior.pizzas.entities.Pizza;

public class PizzaDTO {

    private Integer id;
    private String name;
    private String description;
    private Double price;

    public PizzaDTO() {
    }

    public PizzaDTO(Pizza pizza) {
        this.id = pizza.getId();
        this.name = pizza.getName();
        this.description = pizza.getDescription();
        this.price = pizza.getPrice();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
