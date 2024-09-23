package com.gpads.moringa;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;



@SpringBootApplication
public class MoringaApplication {


	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		SpringApplication.run(MoringaApplication.class, args);
	}
}
