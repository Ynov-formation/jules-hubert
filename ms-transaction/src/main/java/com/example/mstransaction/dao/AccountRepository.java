package com.example.mstransaction.dao;

import com.example.mstransaction.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
    AccountEntity findByAccountID(UUID accountID);
}
