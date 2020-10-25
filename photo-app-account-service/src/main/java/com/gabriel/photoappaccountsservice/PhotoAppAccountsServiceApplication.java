package com.gabriel.photoappaccountsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PhotoAppAccountsServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(PhotoAppAccountsServiceApplication.class, args);
	}
}
