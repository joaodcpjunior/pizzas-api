package com.joaodcpjunior.pizzas;

import com.joaodcpjunior.pizzas.entities.Pizza;
import com.joaodcpjunior.pizzas.repositories.PizzaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PizzasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzasApplication.class, args);
	}

	@Bean
	CommandLineRunner run(PizzaRepository pizzaRepository) {
		return args -> {
			pizzaRepository.save(new Pizza(null, "Calabresa", "Calabresa, mussarela e molho de tomate", 29.90));
			pizzaRepository.save(new Pizza(null, "Calabresa", "Calabresa, mussarela e molho de tomate", 29.90));
			pizzaRepository.save(new Pizza(null, "Calabresa", "Calabresa, mussarela e molho de tomate", 29.90));
			pizzaRepository.save(new Pizza(null, "Calabresa", "Calabresa, mussarela e molho de tomate", 29.90));
			pizzaRepository.save(new Pizza(null, "Calabresa", "Calabresa, mussarela e molho de tomate", 29.90));
		};
	}

}
