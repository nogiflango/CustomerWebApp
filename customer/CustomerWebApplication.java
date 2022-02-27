package com.raj.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan
public class CustomerWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerWebApplication.class, args);

	}

}

