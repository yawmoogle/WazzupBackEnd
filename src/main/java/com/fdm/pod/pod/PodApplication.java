package com.fdm.pod.pod;

import com.fdm.pod.pod.model.Role;
import com.fdm.pod.pod.repository.RoleRepository;
import com.fdm.pod.pod.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PodApplication {

	public static void main(String[] args) {
		SpringApplication.run(PodApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserService userService){
		return args -> {
			roleRepository.save(new Role(1, "USER"));
			/*
			ApplicationUser u = new ApplicationUser();

			u.setFirstName("Faris");
			u.setLastName("Low");

			userService.registerUser(u);
			*/
		};

	}

}
