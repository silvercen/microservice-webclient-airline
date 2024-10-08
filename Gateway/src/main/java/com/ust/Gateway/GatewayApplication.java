package com.ust.Gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
//		System.setProperty("server.port", "9000");
		SpringApplication.run(GatewayApplication.class, args);
	}

}
