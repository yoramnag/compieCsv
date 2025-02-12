package com.example.compieCsv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CompieCsvApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompieCsvApplication.class, args);
	}

}
