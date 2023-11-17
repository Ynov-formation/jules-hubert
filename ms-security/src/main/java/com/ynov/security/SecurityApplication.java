package com.ynov.security;

import com.ynov.security.config.RsaKeysConfig;
import com.ynov.security.dao.UserRepository;
import com.ynov.security.entities.Role;
import com.ynov.security.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeysConfig.class)
public class SecurityApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


	@Override
	public void run(String... args) throws Exception {

		if(userRepository.findByEmail("admin@ynov.com").isEmpty()){
			User user = User.builder()
					.email("admin@ynov.com")
					.password(passwordEncoder().encode("1234"))
					.firstName("admin")
					.lastName("admin")
					.role(Role.ADMIN)
					.build();
			userRepository.save(user);
		}

		if(userRepository.findByEmail("user@ynov.com").isEmpty()){
			User user = User.builder()
					.email("user@ynov.com")
					.password(passwordEncoder().encode("1234"))
					.firstName("user")
					.lastName("user")
					.role(Role.USER)
					.build();
			userRepository.save(user);
		}

	}
}
