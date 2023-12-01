package com.example.mstransaction.entities;

import com.example.mstransaction.core.TransactionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction")
@Entity
public class TransactionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "amout")
    private double amount;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "customer_id")
    private UUID customerID;

    @Column(name = "account_id")
    private UUID accountID;

    @Column(name = "time_creation")
    @JsonFormat(pattern="dd-MM-yyyy HHmm")
    private LocalDate timeCreation = LocalDate.now();

}
