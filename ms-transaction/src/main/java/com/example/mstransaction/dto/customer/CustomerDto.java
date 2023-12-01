package com.example.mstransaction.dto.customer;

import com.example.mstransaction.core.CustomerType;
import com.example.mstransaction.dto.account.AccountResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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

    @JsonProperty("accounts")
    private List<AccountResponse> accounts;

}
