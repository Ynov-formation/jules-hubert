package com.example.mstransaction.mapper;

import com.example.mstransaction.dto.transaction.TransactionRequest;
import com.example.mstransaction.dto.transaction.TransactionResponse;
import com.example.mstransaction.entities.TransactionEntity;
import org.mapstruct.Mapper;
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
