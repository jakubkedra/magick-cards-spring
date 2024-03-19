package com.kedra;

import com.kedra.AuthorizationService.models.Role;
import com.kedra.AuthorizationService.models.UserEntity;
import com.kedra.AuthorizationService.repository.RoleRepository;
import com.kedra.AuthorizationService.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
		return args ->{
			if(roleRepository.findByName("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role(1,"ADMIN"));
			roleRepository.save(new Role(2,"USER"));

			List<Role> roles = new ArrayList<>();
			roles.add(adminRole);

			UserEntity admin = new UserEntity(1, "admin", passwordEncode.encode("password"), roles);

			userRepository.save(admin);
		};
	}

}
