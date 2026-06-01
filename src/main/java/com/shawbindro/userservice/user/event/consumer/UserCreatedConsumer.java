package com.shawbindro.userservice.user.event.consumer;

import com.shawbindro.userservice.user.event.dto.UserCreatedEvent;
import com.shawbindro.userservice.user.event.topics.KafkaTopics;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserCreatedConsumer {

    @KafkaListener(
            topics = KafkaTopics.USER_CREATED,
            groupId = "user-service-group"
    )
    public void consume(UserCreatedEvent event) {

        log.info(
                "Received USER_CREATED event for email={}",
                event.getEmail()
        );

        log.info("Event payload={}", event);
    }
}
