package com.eLibraryClient.applicationWebClientweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients("com.eLibraryClient")
@ComponentScan(basePackages = {"com.eLibraryClient"})
public class ApplicationWebClient {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationWebClient.class, args);
	}

}
