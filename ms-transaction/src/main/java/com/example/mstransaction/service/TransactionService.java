package com.example.mstransaction.service;

import com.example.mstransaction.dao.TransactionRepository;
import com.example.mstransaction.dto.TransactionResponse;
import com.example.mstransaction.dto.TransactionRequest;
import com.example.mstransaction.entities.TransactionEntity;
import com.example.mstransaction.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {
    private final TransactionRepository repository;
    private static final TransactionMapper mapper = TransactionMapper.instance;

    public UUID createTransaction(TransactionRequest request) {
        TransactionEntity transaction = repository.save(mapper.fromTransactionRequest(request));
        return transaction.getId();
    }

    public UUID createTransactionFromAccountToAccount(TransactionRequest requestFrom, TransactionRequest requestTo) {
        TransactionEntity transactionFrom =repository.save(mapper.fromTransactionRequest(requestFrom));
        repository.save(mapper.fromTransactionRequest(requestTo));
        return transactionFrom.getId();
    }

    public List<TransactionResponse> getAllTransactionsFromUser(UUID customerID) {
        return mapper.toTransactionResponseList(repository.findByCustomerID(customerID));
    }
}
