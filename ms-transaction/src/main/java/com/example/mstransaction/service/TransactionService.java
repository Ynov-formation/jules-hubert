package com.example.mstransaction.service;

import com.example.mstransaction.dao.TransactionRepository;
import com.example.mstransaction.dto.transaction.TransactionResponse;
import com.example.mstransaction.dto.transaction.TransactionRequest;
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
    private final AccountService accountService;
    private static final TransactionMapper mapper = TransactionMapper.instance;

    public UUID createTransaction(TransactionRequest request) throws Exception{
        accountService.onTransaction(request);
        return repository.save(mapper.fromTransactionRequest(request)).getId();
    }

    public UUID createTransactionFromAccountToAccount(TransactionRequest requestFrom, TransactionRequest requestTo) throws Exception {
        accountService.onTransfer(requestFrom, requestTo);
        repository.save(mapper.fromTransactionRequest(requestTo));
        return repository.save(mapper.fromTransactionRequest(requestFrom)).getId();
    }

    public List<TransactionResponse> getAllTransactionsFromUser(UUID customerID) {
        return mapper.toTransactionResponseList(repository.findByCustomerID(customerID));
    }


}
