package com.araujo.araujofood;

import com.araujo.araujofood.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AraujoFoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AraujoFoodApiApplication.class, args);
	}

}
