package com.shawbindro.userservice.user.event.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedEvent {

    private String eventId;

    private String eventType;

    private String version;

    private Instant createdAt;

    private String userId;

    private String email;

    private List<String> roles;
}
