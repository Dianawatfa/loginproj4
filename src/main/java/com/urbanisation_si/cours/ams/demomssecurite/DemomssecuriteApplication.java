package com.urbanisation_si.cours.ams.demomssecurite;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.urbanisation_si.cours.ams.demomssecurite.dao.RoleRepository;
import com.urbanisation_si.cours.ams.demomssecurite.modele.Role;


@SpringBootApplication
public class DemomssecuriteApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemomssecuriteApplication.class, args);
	}
	@Bean
	CommandLineRunner init(RoleRepository roleRepository) {
	    return args -> {
	        Role adminRole = roleRepository.findByRole("ADMIN");
	        if (adminRole == null) {
	            Role newAdminRole = new Role();
	            newAdminRole.setRole("ADMIN");
	            roleRepository.save(newAdminRole);
	        }
	        Role userRole = roleRepository.findByRole("USER");
	        if (userRole == null) {
	            Role newUserRole = new Role();
	            newUserRole.setRole("USER");
	            roleRepository.save(newUserRole);
	        }
	    };
	}
}


