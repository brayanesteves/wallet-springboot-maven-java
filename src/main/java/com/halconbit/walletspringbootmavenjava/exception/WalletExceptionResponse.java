package com.halconbit.walletspringbootmavenjava.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WalletExceptionResponse {
    private String reference;
}