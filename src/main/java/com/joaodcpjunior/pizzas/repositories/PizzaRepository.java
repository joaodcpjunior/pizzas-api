package com.joaodcpjunior.pizzas.repositories;

import com.joaodcpjunior.pizzas.entities.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
}
