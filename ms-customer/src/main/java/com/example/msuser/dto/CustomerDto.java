package com.example.msuser.dto;

import com.example.msuser.core.CustomerType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CustomerDto {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("email")
    private String email;

    @JsonProperty("userType")
    private CustomerType userType;

}
