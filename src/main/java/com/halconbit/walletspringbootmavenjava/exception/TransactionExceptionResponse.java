package com.halconbit.walletspringbootmavenjava.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionExceptionResponse {
    private String reference;
}