package com.example.msuser.controller;

import com.example.msuser.dto.CustomerDto;
import com.example.msuser.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/user")
public class CustomerController {
    @Autowired
    private CustomerService userService;

    @GetMapping(path = "/{userId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable UUID userId) {

        CustomerDto userDto = userService.getCustomerById(userId);

        return userDto == null
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.ok(userDto);
    }

    @PostMapping
    @ApiOperation(value = "save a new user and return its ID", response = UUID.class)
    public ResponseEntity<UUID> createCustomer(@RequestBody CustomerDto userDto){
        return new ResponseEntity<>(userService.saveCustomer(userDto), HttpStatus.CREATED);
    }

}