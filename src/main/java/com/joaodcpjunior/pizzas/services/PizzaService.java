package com.joaodcpjunior.pizzas.services;

import com.joaodcpjunior.pizzas.dtos.PizzaDTO;
import com.joaodcpjunior.pizzas.entities.Pizza;
import com.joaodcpjunior.pizzas.repositories.PizzaRepository;
import com.joaodcpjunior.pizzas.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    public void registerPizza(Pizza pizza) {
        pizzaRepository.save(pizza);
    }

    public Page<Pizza> findAll(Pageable pageable) {
        return pizzaRepository.findAll(pageable);
    }

    public Pizza findById(Integer id) {
        Pizza pizza = pizzaRepository.findById(id).orElse(null);
        if (pizza == null) {
            throw new ObjectNotFoundException("Object not found");
        }
        return pizza;
    }

    public void update(Pizza pizza, Integer id) {
        Pizza updatedPizza = findById(id);
        updateData(updatedPizza, pizza);
        pizzaRepository.save(updatedPizza);
    }

    private void updateData(Pizza updatedPizza, Pizza pizza) {
        updatedPizza.setName(pizza.getName());
        updatedPizza.setDescription(pizza.getDescription());
        updatedPizza.setPrice(pizza.getPrice());
    }

    public void delete(Integer id) {
        findById(id);
        pizzaRepository.deleteById(id);
    }

    public Pizza fromDto(PizzaDTO pizzaDto) {
        return new Pizza(pizzaDto.getId(), pizzaDto.getName(), pizzaDto.getDescription(), pizzaDto.getPrice());
    }
}
