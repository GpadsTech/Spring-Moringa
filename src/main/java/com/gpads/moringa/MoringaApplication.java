package com.gpads.moringa;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;



@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.gpads.moringa.repositories")
public class MoringaApplication {


	public static void main(String[] args) {
		SpringApplication.run(MoringaApplication.class, args);
	}
}
