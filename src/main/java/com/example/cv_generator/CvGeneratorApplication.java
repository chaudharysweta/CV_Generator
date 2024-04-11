package com.example.cv_generator;

import com.example.cv_generator.config.AppConstants;
import com.example.cv_generator.entity.Role;
import com.example.cv_generator.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class CvGeneratorApplication implements CommandLineRunner {

	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public CvGeneratorApplication(RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(CvGeneratorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {



	}
}
