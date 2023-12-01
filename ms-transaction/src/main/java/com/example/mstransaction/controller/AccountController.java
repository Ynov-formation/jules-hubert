package com.example.mstransaction.controller;

import com.example.mstransaction.dto.account.AccountRequest;
import com.example.mstransaction.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping
public class AccountController {
    @Autowired
    private AccountService service;

    public AccountController(){}

    @PostMapping
    @RequestMapping(value ="/account/{accountID}/")
    public ResponseEntity<?> getAccountBalance(@RequestBody UUID accountID) {
        double result = service.getAccountDetails(accountID);
       return ResponseEntity.ok("Transaction ID :"+result);
    }

    @PostMapping
    @RequestMapping(value ="/create-account")
    public ResponseEntity<?> createAccount(@RequestBody AccountRequest request){
        UUID result = service.saveAccount(request);
        if(result == null)return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok("Account ID :"+result);
    }

    @DeleteMapping
    @RequestMapping(value ="/delete-account/{accountID}/")
    public ResponseEntity<?> deleteAccount(@PathVariable UUID accountID){
        service.deleteAccount(accountID);
        return ResponseEntity.ok("Account ID :"+accountID);
    }
}
