package com.davidesdras.bazar;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.davidesdras.bazar.model.repositories.ConnectionManager;

@SpringBootApplication
public class BazarApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(BazarApplication.class, args);
	}

}
