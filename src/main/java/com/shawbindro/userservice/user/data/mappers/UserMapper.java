package com.shawbindro.userservice.user.data.mappers;

import com.shawbindro.userservice.user.dtos.AddressResponse;
import com.shawbindro.userservice.user.dtos.UserResponse;
import com.shawbindro.userservice.user.models.Address;
import com.shawbindro.userservice.user.models.User;

public class UserMapper {

    private UserMapper() {} // prevent instantiation

    public static UserResponse toResponse(User user) {

        return UserResponse.builder()
                .id(String.valueOf(user.getId()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .fullName(user.getFirstName() + " " + user.getLastName())
                .email(user.getEmail())
                .userName(user.getUsername())
                .phone(user.getPhone())
                .role(user.getRole())
                .address(mapAddress(user.getAddress()))
                .build();
    }

    private static AddressResponse mapAddress(Address address) {

        if (address == null) return null;

        return AddressResponse.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .zipcode(address.getZipcode())
                .build();
    }
}
