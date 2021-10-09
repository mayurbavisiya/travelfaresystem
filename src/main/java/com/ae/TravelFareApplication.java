package com.ae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TravelFareApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelFareApplication.class, args);
	}

}
