package com.example.mstransaction.mapper;

import com.example.mstransaction.dto.account.AccountRequest;
import com.example.mstransaction.dto.account.AccountResponse;
import com.example.mstransaction.entities.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper
public interface AccountMapper {
    AccountMapper instance = Mappers.getMapper(AccountMapper.class);
    AccountEntity fromAccountRequest(AccountRequest accountRequest);
    AccountResponse toAccountResponse(AccountEntity account);
    AccountRequest toAccountRequest(Optional<AccountEntity> account);
}
