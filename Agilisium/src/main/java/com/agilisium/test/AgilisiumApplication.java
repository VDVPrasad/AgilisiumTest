package com.agilisium.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.agilisium.test.product.entities.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication(scanBasePackages={"com.agilisium.test"})
public class AgilisiumApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgilisiumApplication.class, args);
	}
	
	
}
