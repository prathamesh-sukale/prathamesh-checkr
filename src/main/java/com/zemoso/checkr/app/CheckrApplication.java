package com.zemoso.checkr.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.zemoso")
public class CheckrApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckrApplication.class, args);
	}

}
