package com.example.mstransaction.controller;

import com.example.mstransaction.dto.transaction.TransactionResponse;
import com.example.mstransaction.dto.transaction.TransactionRequest;
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

    @GetMapping
    @RequestMapping(value ="/user-transactions/{customerID}/")
    public ResponseEntity<?> getAllTransactionsFromUserId(@PathVariable UUID customerID){

        List<TransactionResponse> result = service.getAllTransactionsFromUser(customerID);

        if(result.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(result);
    }

    @PostMapping
    @RequestMapping(value ="/create-transaction")
    public ResponseEntity<?> createTransaction(@RequestBody TransactionRequest request) throws Exception {
        UUID result = service.createTransaction(request);
        if(result == null)return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok("Transaction ID :"+result);
    }

    @PostMapping
    @RequestMapping(value ="/create-transaction-from-to")
    public ResponseEntity<?> createTransactionToAnotherAccount(@RequestBody TransactionRequest requestFrom, @RequestBody TransactionRequest requestTo) throws Exception {
        UUID result = service.createTransactionFromAccountToAccount(requestFrom, requestTo);
        if(result == null)return ResponseEntity.internalServerError().build();
        return new ResponseEntity<>("Transaction ID :"+result, HttpStatus.CREATED);
    }
}