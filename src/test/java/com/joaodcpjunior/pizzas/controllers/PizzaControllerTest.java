package com.joaodcpjunior.pizzas.controllers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

import com.joaodcpjunior.pizzas.dtos.PizzaDTO;
import com.joaodcpjunior.pizzas.entities.Pizza;
import com.joaodcpjunior.pizzas.services.PizzaService;
import com.joaodcpjunior.pizzas.services.exceptions.ObjectNotFoundException;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@WebMvcTest
public class PizzaControllerTest {

    @Autowired
    private PizzaController pizzaController;

    @MockBean
    private PizzaService pizzaService;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.pizzaController);
    }

    @Test
    public void mustReturnSuccess_WhenFindById() {

        Mockito.when(this.pizzaService.findById(1))
            .thenReturn(new Pizza(1, "Mussarela", "Mussarela", 39.90));

        given()
                .accept(ContentType.JSON)
                .when()
                .get("api/v1/pizzas/{id}", 1L)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}