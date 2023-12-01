package com.example.mstransaction.dto.account;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class AccountResponse {
    private UUID accountID;
    private double balance;
    private LocalDate timeCreation;
    private UUID customerId;
}
