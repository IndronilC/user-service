package com.shawbindro.userservice.user.dtos;


import com.shawbindro.userservice.user.models.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String id;
    private String keyCloakId;
    private String firstName;
    private String lastName;
    private String fullName;
    private String userName;
    private String email;
    private String phone;
    private UserRole role;
    private AddressResponse address;
}
