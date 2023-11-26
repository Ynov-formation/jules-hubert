package com.example.mstransaction.mapper;

import com.example.mstransaction.dto.TransactionRequest;
import com.example.mstransaction.dto.TransactionResponse;
import com.example.mstransaction.entities.TransactionEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TransactionMapper {
    TransactionMapper instance = Mappers.getMapper(TransactionMapper.class);
    TransactionEntity fromTransactionRequest(TransactionRequest transactionRequest);
    TransactionEntity fromTransactionResponse(TransactionResponse transactionResponse);
    TransactionResponse toTransactionResponse(TransactionEntity transaction);
    List<TransactionResponse> toTransactionResponseList(List<TransactionEntity> transactionEntityList);

}
