package com.joaodcpjunior.pizzas.controllers;

import com.joaodcpjunior.pizzas.entities.Pizza;
import com.joaodcpjunior.pizzas.services.PizzaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/pizzas")
public class PizzaController {


    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @PostMapping
    public ResponseEntity<Void> registerPizza(@RequestBody Pizza pizza) {
        pizzaService.registerPizza(pizza);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pizza.getId()).toUri();
        return  ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<Pizza>> findAll(){
        List<Pizza> pizzaList = pizzaService.findAll();
        return ResponseEntity.ok().body(pizzaList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pizza> findById(@PathVariable Integer id) {
        Pizza pizzaObj = pizzaService.findById(id);
        return ResponseEntity.ok().body(pizzaObj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateUser(@RequestBody Pizza pizza, @PathVariable Integer id) {
        pizzaService.update(pizza, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        pizzaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}