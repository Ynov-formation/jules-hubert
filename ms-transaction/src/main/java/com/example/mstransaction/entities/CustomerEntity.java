package com.example.mstransaction.entities;

import com.example.mstransaction.core.CustomerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class CustomerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private CustomerType userType;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<AccountEntity> accounts;

}
