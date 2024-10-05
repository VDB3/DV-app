package com.ok.dvweb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DVApplication {
	private static final String SERVER_UP = "App started.";

	public static void main(String[] args) {
		log.warn(SERVER_UP);
		SpringApplication.run(DVApplication.class, args);
	}

}
