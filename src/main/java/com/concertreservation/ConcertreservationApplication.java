package com.concertreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ConcertreservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConcertreservationApplication.class, args);
	}

}
