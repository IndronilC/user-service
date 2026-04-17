package com.shawbindro.userservice.user.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponse {
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}
