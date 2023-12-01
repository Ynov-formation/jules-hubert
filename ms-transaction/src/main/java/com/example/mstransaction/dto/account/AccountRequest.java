package com.example.mstransaction.dto.account;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class AccountRequest {
    private UUID customerId;
    private double balance;
}
