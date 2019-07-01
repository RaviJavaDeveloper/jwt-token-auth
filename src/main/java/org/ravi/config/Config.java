package org.ravi.config;

import org.ravi.models.User;
import org.ravi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	
	@Autowired
	private UserRepository userRepository;
	
	@Bean
	public CommandLineRunner createUsers() {
		return strings -> {
			System.out.println("Saving users");
			userRepository.save(new User("ravi", "ravi54", "ravi@gmail.com", "ADMIN"));
			userRepository.save(new User("ravi2", "ravi542", "ravi2@	.com", "USER"));
		};
	}
}
