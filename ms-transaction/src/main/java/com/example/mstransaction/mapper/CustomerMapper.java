package com.example.mstransaction.mapper;

import com.example.mstransaction.dto.customer.CustomerDto;
import com.example.mstransaction.entities.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper instance = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "id", ignore = true)
    CustomerEntity toCustomer(CustomerDto customerDto);
    CustomerDto toCustomerDto(CustomerEntity customer);

}
