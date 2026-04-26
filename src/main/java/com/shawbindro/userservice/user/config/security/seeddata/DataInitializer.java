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

            String customerEmailFirst = "chakraborty.indranil@gmail.com";
            String customerEmailSecond = "sabaridatta@gmail.com";

            // First user
            if (userRepository.findByEmail(customerEmailFirst).isEmpty()) {
                User user1 = new User();
                user1.setUsername("Indranil Chakraborty");
                user1.setFirstName("Indranil");
                user1.setLastName("Chakraborty");
                user1.setEmail(customerEmailFirst);

                userRepository.save(user1);
                log.info("✅ Seed user created: " + customerEmailFirst);
            }

            // Second user
            if (userRepository.findByEmail(customerEmailSecond).isEmpty()) {
                User user2 = new User();
                user2.setUsername("Sabari Dutta");
                user2.setFirstName("Sabari");
                user2.setLastName("Dutta");
                user2.setEmail(customerEmailSecond);

                userRepository.save(user2);
                log.info("✅ Seed user created: " + customerEmailSecond);
            }
        };
    }
  }
