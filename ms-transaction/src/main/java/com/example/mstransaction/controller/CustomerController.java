package com.example.mstransaction.controller;

import com.example.mstransaction.dto.customer.CustomerDto;
import com.example.mstransaction.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(path = "/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable UUID customerId) {

        if (customerId == null){
            return new ResponseEntity<>("customerId can't be null", HttpStatus.BAD_REQUEST);
        }

        CustomerDto customerDto = customerService.getCustomerById(customerId);

        return customerDto == null
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.ok(customerDto);
    }

    @PostMapping
    @ApiOperation(value = "save a new customer and return its ID", response = UUID.class)
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDto customerDto){
        if (customerDto == null){
            return new ResponseEntity<>("customer can't be null", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customerService.saveCustomer(customerDto), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation(value = "update a customer and return its ID", response = UUID.class)
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDto customerDto){
        if (customerDto == null){
            return new ResponseEntity<>("customer can't be null", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customerService.saveCustomer(customerDto), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCustomer(UUID customerId){
        if (customerId == null){
            return new ResponseEntity<>("customerId can't be null", HttpStatus.BAD_REQUEST);
        }
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.ok().build();
    }

}