package com.jet.restaurant.event.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@EnableKafka
@EnableKafkaStreams
@SpringBootApplication
public class RestaurantEventServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantEventServiceApplication.class, args);
	}

}
