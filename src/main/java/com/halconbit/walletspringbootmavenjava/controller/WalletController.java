package com.halconbit.walletspringbootmavenjava.controller;

import com.halconbit.walletspringbootmavenjava.entity.Wallet;
import com.halconbit.walletspringbootmavenjava.services.HandleErrorService;
import com.halconbit.walletspringbootmavenjava.services.WalletService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
@CrossOrigin
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private HandleErrorService handleErrorService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.walletService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find-all-order-by-priority")
    public ResponseEntity<?> findAllOrderByPriority() {
        return new ResponseEntity<>(this.walletService.findAllByOrderByPriority(), HttpStatus.OK);
    }

    @GetMapping("/{reference}")
    public ResponseEntity<?> findByReference(@PathVariable Long reference) {
        return new ResponseEntity<>(this.walletService.findByReference(reference), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Wallet wallet, BindingResult bindingResult) {
        ResponseEntity responseEntityErrors = this.handleErrorService.validate(bindingResult);
        if(responseEntityErrors != null) {
            return responseEntityErrors;
        }
        Wallet walletCreateOrUpdate = this.walletService.createOrUpdate(wallet);
        return new ResponseEntity<Wallet>(walletCreateOrUpdate, HttpStatus.CREATED);
    }

    @PutMapping("/{reference}")
    public ResponseEntity<?> update(@PathVariable Long reference, @RequestBody Wallet wallet, BindingResult bindingResult) {
        ResponseEntity responseEntityErrors = this.handleErrorService.validate(bindingResult);
        if(responseEntityErrors != null) {
            return responseEntityErrors;
        }
        wallet.setReference(reference);
        Wallet walletCreateOrUpdate = this.walletService.createOrUpdate(wallet);
        return new ResponseEntity<Wallet>(walletCreateOrUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{reference}")
    public ResponseEntity<?> delete(@PathVariable Long reference) {
        this.walletService.delete(reference);
        return new ResponseEntity(HttpStatus.OK);
    }

}