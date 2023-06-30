package com.zemoso.checkr.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = "com.zemoso.checkr.*")
@EnableJpaRepositories("com.zemoso.checkr.*")
@EntityScan("com.zemoso.checkr.*")
@EnableJpaAuditing
@OpenAPIDefinition
public class CheckrApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckrApplication.class, args);
	}

	@Autowired
	private ObjectMapper objectMapper;

	@PostConstruct
	private void setUp(){
		objectMapper.registerModule(new JavaTimeModule());
	}

}
