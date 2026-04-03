package com.shawbindro.userservice.user.config.security.seeddata;

import com.shawbindro.userservice.user.models.User;
import com.shawbindro.userservice.user.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Slf4j
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {

            String email = "chakraborty.indranil@gmail.com";

            // ✅ Check if user already exists
            if (userRepository.findByEmail(email).isEmpty()) {

                User user = new User();
              //👈 must match JWT if using userId later
                user.setUsername("Indranil Chakraborty");
                user.setFirstName("Indranil");
                user.setLastName("Chakraborty");
                user.setEmail(email);

                userRepository.save(user);

                log.info("✅ Seed user created: " + email);
            } else {
                log.info("ℹ️ Seed user already exists");
            }
        };
    }
}