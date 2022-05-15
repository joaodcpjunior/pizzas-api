package com.joaodcpjunior.pizzas.controllers;

import com.joaodcpjunior.pizzas.dtos.PizzaDTO;
import com.joaodcpjunior.pizzas.entities.Pizza;
import com.joaodcpjunior.pizzas.exceptions.StandardError;
import com.joaodcpjunior.pizzas.services.PizzaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @PostMapping
    @Operation(
            summary = "Register a new Pizza",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created"),
                    @ApiResponse(responseCode = "404", description = "Object not Found")
            }
    )
    public ResponseEntity<Void> registerPizza(@RequestBody PizzaDTO pizzaDto) {
        Pizza pizza = pizzaService.fromDto(pizzaDto);
        pizzaService.registerPizza(pizza);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pizza.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    @Operation(
            summary = "Find all Pizzas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
            }
    )
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<Pizza> pizzaList = pizzaService.findAll(pageable);
        Page<PizzaDTO> pizzaDTOList = pizzaList.map(pizza -> new PizzaDTO(pizza));
        return ResponseEntity.ok().body(pizzaDTOList);
    }

    @GetMapping(value = "/{id}")
    @Operation(
            summary = "Find a Pizza by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PizzaDTO.class)
                    )),
                    @ApiResponse(responseCode = "404", description = "Object not Found", content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StandardError.class)
                    ))
            }
    )
    public ResponseEntity<PizzaDTO> findById(@PathVariable Integer id) {
        Pizza pizzaObj = pizzaService.findById(id);
        return ResponseEntity.ok().body(new PizzaDTO(pizzaObj));
    }

    @PutMapping(value = "/{id}")
    @Operation(
            summary = "Update a Pizza by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
                    @ApiResponse(responseCode = "404", description = "Object not Found")
            }
    )
    public ResponseEntity<Void> update(@RequestBody PizzaDTO pizzaDTO, @PathVariable Integer id) {
        Pizza pizzaObj = pizzaService.fromDto(pizzaDTO);
        pizzaObj.setId(id);
        pizzaService.update(pizzaObj, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    @Operation(
            summary = "Delete a Pizza by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
                    @ApiResponse(responseCode = "404", description = "Object not Found")
            }
    )
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        pizzaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}