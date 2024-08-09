package com.savindu.cardManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class CardManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardManagementApplication.class, args);
	}

}
