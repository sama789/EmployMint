package com.spring.DackendJuniorTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.spring.DackendJuniorTask")
public class DackendJuniorTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(DackendJuniorTaskApplication.class, args);
	}

}
