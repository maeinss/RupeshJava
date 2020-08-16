package com.tts.techtalenttwitter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
//@RestController
public class TechtalenttwitterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechtalenttwitterApplication.class, args);
	}

}
