package com.example.mstransaction.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
@Entity
public class AccountEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountID;

    @Column(name = "balance")
    private double balance;

    @Column(name = "time_creation")
    @JsonFormat(pattern="dd-MM-yyyy HHmm")
    private LocalDate timeCreation = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
