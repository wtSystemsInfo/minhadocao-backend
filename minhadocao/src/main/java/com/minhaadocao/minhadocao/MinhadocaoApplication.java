package com.minhaadocao.minhadocao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan(basePackages = "com.minhaadocao.minhadocao.models.entities")
@SpringBootApplication
public class MinhadocaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinhadocaoApplication.class, args);
	}

}
