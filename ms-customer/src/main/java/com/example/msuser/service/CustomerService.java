package com.example.msuser.service;

import com.example.msuser.dao.CustomerRepository;
import com.example.msuser.dto.CustomerDto;
import com.example.msuser.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository userRepository;
    private static final CustomerMapper userMapper = CustomerMapper.instance;

    public UUID saveCustomer(CustomerDto userDto) {
        return Optional.of(userDto)
                .map(userMapper::toCustomer)
                .map(userRepository::save)
                .map(savedCustomer -> savedCustomer.getId())
                .orElseThrow();
    }

    public CustomerDto getCustomerById(UUID userId){
        return userRepository.findById(userId)
                .map(userMapper::toCustomerDto)
                .orElse(null);
    }
}
