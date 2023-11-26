package com.example.mstransaction.dto;

import com.example.mstransaction.core.TransactionType;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
public class TransactionRequest {
    private String name;
    private double amount;
    private TransactionType type;
    private UUID customerID;
    private UUID accountID;
}
