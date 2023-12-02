package com.example.mstransaction.controller;

import com.example.mstransaction.dto.account.AccountRequest;
import com.example.mstransaction.service.AccountService;
import com.example.mstransaction.service.SecurityServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private SecurityServiceClient securityServiceClient;

    @Autowired
    public void CustomerService(SecurityServiceClient securityServiceClient) {
        this.securityServiceClient = securityServiceClient;
    }

    private boolean verifiyIfTokenIsValid(String token){

        ResponseEntity<?> securityServiceResponse = securityServiceClient.getIsTokenValid(token);

        if (securityServiceResponse.getBody() == "true"){
            return true;
        }
        return false;
    }

    @PostMapping
    @RequestMapping(value ="/account/{accountID}/")
    public ResponseEntity<?> getAccountBalance(@RequestBody UUID accountID, @RequestParam String token) {

        if(token == null){
            return new ResponseEntity<>("token can't be null", HttpStatus.UNAUTHORIZED);
        }

        if(!verifiyIfTokenIsValid(token)){
            return new ResponseEntity<>("token can't be null", HttpStatus.UNAUTHORIZED);
        }

        double result = service.getAccountDetails(accountID);
       return ResponseEntity.ok("Transaction ID :"+result);
    }

    @PostMapping
    @RequestMapping(value ="/create-account")
    public ResponseEntity<?> createAccount(@RequestBody AccountRequest request, @RequestParam String token) throws Exception {

        if(token == null){
            return new ResponseEntity<>("token can't be null", HttpStatus.UNAUTHORIZED);
        }

        if(!verifiyIfTokenIsValid(token)){
            return new ResponseEntity<>("token can't be null", HttpStatus.UNAUTHORIZED);
        }

        UUID result = service.saveAccount(request);
        if(result == null)return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok("Account ID :"+result);
    }

    @DeleteMapping
    @RequestMapping(value ="/delete-account/")
    public ResponseEntity<?> deleteAccount(@RequestParam UUID accountID, @RequestParam String token){

        if(token == null){
            return new ResponseEntity<>("token can't be null", HttpStatus.UNAUTHORIZED);
        }

        if(!verifiyIfTokenIsValid(token)){
            return new ResponseEntity<>("token can't be null", HttpStatus.UNAUTHORIZED);
        }

        service.deleteAccount(accountID);
        return ResponseEntity.ok("Account ID :"+accountID);
    }
}
