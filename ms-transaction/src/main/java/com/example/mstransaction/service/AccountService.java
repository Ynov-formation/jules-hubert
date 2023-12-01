package com.example.mstransaction.service;


import com.example.mstransaction.core.TransactionType;
import com.example.mstransaction.dao.AccountRepository;
import com.example.mstransaction.dto.account.AccountRequest;
import com.example.mstransaction.dto.account.AccountResponse;
import com.example.mstransaction.dto.transaction.TransactionRequest;
import com.example.mstransaction.entities.AccountEntity;
import com.example.mstransaction.mapper.AccountMapper;
import jakarta.ws.rs.InternalServerErrorException;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final AccountRepository repository;
    private final CustomerService customerService;
    private static final AccountMapper mapper = AccountMapper.instance;

    public double getAccountDetails(UUID accountID) {
        return repository.findByAccountID(accountID).getBalance();
    }

    private boolean doesAccountExist(UUID accountID) {
        return repository.existsById(accountID);
    }

    public UUID saveAccount(AccountRequest request) {
        try{
            if(!customerService.doesCustomerExist(request.getCustomerId())){
                throw new NotFoundException("Not found");
            }
            return Optional.of(request)
                    .map(mapper::fromAccountRequest)
                    .map(repository::save)
                    .map(AccountEntity::getAccountID)
                    .orElseThrow();
        } catch (Exception e){
            throw new InternalServerErrorException();
        }
    }

    public void deleteAccount(UUID accountID) {
        repository.delete(repository.findByAccountID(accountID));
    }

    public void onTransaction(TransactionRequest request) {
        try{
            if (!doesAccountExist(request.getAccountID())){
                throw new NotFoundException("Not found");
            }
            AccountRequest model = mapper.toAccountRequest(repository.findByAccountID(request.getAccountID()));
            if (request.getType() == TransactionType.DEBIT) {
                model.setBalance(debit(model.getBalance(), request.getAmount()));
            } else {
                model.setBalance(credit(model.getBalance(), request.getAmount()));
            }
            saveAccount(model);
        }catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    public void onTransfer(TransactionRequest requestFrom, TransactionRequest requestTo) {
        try {
            if (!doesAccountExist(requestFrom.getAccountID()) || !doesAccountExist(requestTo.getAccountID())){
                throw new NotFoundException("Not found");
            }
            if (requestFrom.getType() == requestTo.getType()) {
                throw new IllegalArgumentException();
            }
            onTransaction(requestFrom);
            onTransaction(requestTo);
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    private double debit(double in, double out) {
        return in - out;
    }

    private double credit(double in, double out) {
        return in + out;
    }
}
