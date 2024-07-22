package com.Lubee.Lubee;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@OpenAPIDefinition(servers = {@Server(url = "https://lubee.site", description = "Default Server URL")})
public class LubeeApplication {

	static {System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");}
	public static void main(String[] args) {
		SpringApplication.run(LubeeApplication.class, args);
	}

}
