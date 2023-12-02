package com.example.mstransaction.service;


import com.example.mstransaction.core.TransactionType;
import com.example.mstransaction.dao.AccountRepository;
import com.example.mstransaction.dto.account.AccountRequest;
import com.example.mstransaction.dto.customer.CustomerDto;
import com.example.mstransaction.dto.transaction.TransactionRequest;
import com.example.mstransaction.entities.AccountEntity;
import com.example.mstransaction.mapper.AccountMapper;
import com.example.mstransaction.mapper.CustomerMapper;
import jakarta.ws.rs.InternalServerErrorException;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final AccountRepository repository;
    private final CustomerService customerService;
    private static final AccountMapper accountMapper = AccountMapper.instance;
    private static final CustomerMapper customerMapper = CustomerMapper.instance;

    public double getAccountDetails(UUID accountID) {
        return repository.findById(accountID).get().getBalance();
    }

    private boolean doesAccountExist(UUID accountID) {
        return repository.existsById(accountID);
    }

    public UUID saveAccount(AccountRequest request) throws Exception {
        try{
            if(!customerService.doesCustomerExist(request.getCustomerId())){
                throw new NotFoundException("Not found");
            }

            CustomerDto customer = customerService.getCustomerById(request.getCustomerId());

            AccountEntity account = accountMapper.fromAccountRequest(request);
            account.setCustomer(customerMapper.toCustomer(customer));

            repository.save(account);

            return account.getId();
        } catch (Exception e){
            throw e;
        }
    }

    public void deleteAccount(UUID accountID) {

            repository.deleteById(accountID);

    }

    public void onTransaction(TransactionRequest request) throws Exception {
        try{
            if (!doesAccountExist(request.getAccountID())){
                throw new NotFoundException("Not found");
            }
            Optional<AccountEntity> accountEntity = repository.findById(request.getAccountID());

            AccountRequest model = accountMapper.toAccountRequest(accountEntity);

            if (request.getType() == TransactionType.DEBIT) {
                model.setBalance(debit(model.getBalance(), request.getAmount()));
            } else {
                model.setBalance(credit(model.getBalance(), request.getAmount()));
            }
            saveAccount(model);
        }catch (Exception e) {
            throw e;
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
