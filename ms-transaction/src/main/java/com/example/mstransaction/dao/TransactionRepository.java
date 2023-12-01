package com.example.mstransaction.dao;

import com.example.mstransaction.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {
    List<TransactionEntity> findByCustomerID(UUID customerID);
}

