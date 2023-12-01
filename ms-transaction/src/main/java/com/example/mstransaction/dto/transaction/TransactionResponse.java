package com.example.mstransaction.dto.transaction;

import com.example.mstransaction.core.TransactionType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TransactionResponse {
    private UUID id;
    private String name;
    private double amount;
    private TransactionType type;
    private UUID customerID;
    private UUID accountID;
    private LocalDate timeCreation;
}