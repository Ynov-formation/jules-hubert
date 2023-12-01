package com.example.mstransaction.service;

import com.example.mstransaction.dao.CustomerRepository;
import com.example.mstransaction.dto.account.AccountResponse;
import com.example.mstransaction.dto.customer.CustomerDto;
import com.example.mstransaction.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private static final CustomerMapper customerMapper = CustomerMapper.instance;

    public boolean doesCustomerExist(UUID customerId){
        return customerRepository.existsById(customerId);
    }

    public UUID saveCustomer(CustomerDto customerDto) {
        return Optional.of(customerDto)
                .map(customerMapper::toCustomer)
                .map(customerRepository::save)
                .map(savedCustomer -> savedCustomer.getId())
                .orElseThrow();
    }

    public CustomerDto getCustomerById(UUID customerId){
        return customerRepository.findById(customerId)
                .map(customerMapper::toCustomerDto)
                .orElse(null);
    }

    public void deleteCustomerById(UUID customerId){
        customerRepository.deleteById(customerId);
    }
}
