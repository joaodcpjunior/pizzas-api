package com.joaodcpjunior.pizzas.controllers;

import com.joaodcpjunior.pizzas.dtos.PizzaDTO;
import com.joaodcpjunior.pizzas.entities.Pizza;
import com.joaodcpjunior.pizzas.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @PostMapping
    public ResponseEntity<Void> registerPizza(@RequestBody PizzaDTO pizzaDto) {
        Pizza pizza = pizzaService.fromDto(pizzaDto);
        pizzaService.registerPizza(pizza);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pizza.getId()).toUri();
        return  ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable){
        Page<Pizza> pizzaList = pizzaService.findAll(pageable);
        Page<PizzaDTO> pizzaDTOList = pizzaList.map(pizza -> new PizzaDTO(pizza));
        return ResponseEntity.ok().body(pizzaDTOList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PizzaDTO> findById(@PathVariable Integer id) {
        Pizza pizzaObj = pizzaService.findById(id);
        return ResponseEntity.ok().body(new PizzaDTO(pizzaObj));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody PizzaDTO pizzaDTO, @PathVariable Integer id) {
        Pizza pizzaObj = pizzaService.fromDto(pizzaDTO);
        pizzaObj.setId(id);
        pizzaService.update(pizzaObj, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        pizzaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}