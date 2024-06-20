package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demo", "org.example"})
public class SpringBootApiServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApiServerApplication.class, args);
	}
}
