package com.shawbindro.userservice.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class VirtualThreadExamoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualThreadExamoleApplication.class, args);
		log.info("🚀 Application started");
	}

}
