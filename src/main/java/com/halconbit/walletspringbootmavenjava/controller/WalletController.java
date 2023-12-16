package com.halconbit.walletspringbootmavenjava.controller;

import com.halconbit.walletspringbootmavenjava.entity.Wallet;
import com.halconbit.walletspringbootmavenjava.services.HandleErrorService;
import com.halconbit.walletspringbootmavenjava.services.WalletService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private HandleErrorService handleErrorService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Wallet wallet, BindingResult bindingResult) {
        ResponseEntity responseEntityErrors = this.handleErrorService.validate(bindingResult);
        if(responseEntityErrors != null) {
            return responseEntityErrors;
        }
        Wallet walletCreateOrUpdate = this.walletService.createOrUpdate(wallet);
        return new ResponseEntity<Wallet>(walletCreateOrUpdate, HttpStatus.CREATED);
    }

}