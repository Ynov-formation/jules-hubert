package com.example.mstransaction.controller;

import com.example.mstransaction.dto.transaction.TransactionResponse;
import com.example.mstransaction.dto.transaction.TransactionRequest;
import com.example.mstransaction.service.SecurityServiceClient;
import com.example.mstransaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping
public class TransactionController {
    @Autowired
    private TransactionService service;

    public TransactionController(){}

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

    @GetMapping
    @RequestMapping(value ="/user-transactions")
    public ResponseEntity<?> getAllTransactionsFromUserId(@RequestParam UUID customerID, @RequestParam String token){

        if(token == null){
            return new ResponseEntity<>("token can't be null", HttpStatus.UNAUTHORIZED);
        }

        if(!verifiyIfTokenIsValid(token)){
            return new ResponseEntity<>("token can't be null", HttpStatus.UNAUTHORIZED);
        }

        List<TransactionResponse> result = service.getAllTransactionsFromUser(customerID);

        if(result.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(result);
    }

    @PostMapping
    @RequestMapping(value ="/create-transaction")
    public ResponseEntity<?> createTransaction(@RequestBody TransactionRequest request, @RequestParam String token) throws Exception {

        if(token == null){
            return new ResponseEntity<>("token can't be null", HttpStatus.UNAUTHORIZED);
        }

        if(!verifiyIfTokenIsValid(token)){
            return new ResponseEntity<>("token can't be null", HttpStatus.UNAUTHORIZED);
        }

        UUID result = service.createTransaction(request);
        if(result == null)return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok("Transaction ID :"+result);
    }

    @PostMapping
    @RequestMapping(value ="/create-transaction-from-to")
    public ResponseEntity<?> createTransactionToAnotherAccount(@RequestBody TransactionRequest requestFrom,
                                                               @RequestBody TransactionRequest requestTo,
                                                               @RequestParam String token) throws Exception {

        if(token == null){
            return new ResponseEntity<>("token can't be null", HttpStatus.UNAUTHORIZED);
        }

        if(!verifiyIfTokenIsValid(token)){
            return new ResponseEntity<>("token can't be null", HttpStatus.UNAUTHORIZED);
        }

        UUID result = service.createTransactionFromAccountToAccount(requestFrom, requestTo);
        if(result == null)return ResponseEntity.internalServerError().build();
        return new ResponseEntity<>("Transaction ID :"+result, HttpStatus.CREATED);
    }
}