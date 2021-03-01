package com.warehouse.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
		//TODO: Docker with PostreSql
		//TODO: Refactoring
		//TODO Security- secure api endpoints

	}

}
