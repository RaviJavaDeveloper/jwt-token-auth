package org.ravi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.ravi.repository")
@EnableWebSecurity
public class JwtTokenAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtTokenAuthApplication.class, args);
	}

}
