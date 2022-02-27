package com.raj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Mywebapp1Application {

	public static void main1(String[] args) {
		SpringApplication.run(Mywebapp1Application.class, args);
	}

}
