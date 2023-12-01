package com.example.msuser.mapper;

import com.example.msuser.dto.CustomerDto;
import com.example.msuser.entities.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper instance = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "id", ignore = true)
    CustomerEntity toCustomer(CustomerDto userDto);
    CustomerDto toCustomerDto(CustomerEntity user);

}
