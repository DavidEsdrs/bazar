package com.davidesdras.bazar;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BazarApplication {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		SpringApplication.run(BazarApplication.class, args);

	}

}
