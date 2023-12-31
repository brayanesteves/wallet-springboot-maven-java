package com.halconbit.walletspringbootmavenjava.controller;

import com.halconbit.walletspringbootmavenjava.entity.Transaction;
import com.halconbit.walletspringbootmavenjava.services.HandleErrorService;
import com.halconbit.walletspringbootmavenjava.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@CrossOrigin
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private HandleErrorService handleErrorService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.transactionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find-all-by-wallet/{walletReference}")
    public ResponseEntity<?> findAllByWallet(@PathVariable Long walletReference) {
        return new ResponseEntity<>(this.transactionService.findAllByWallet(walletReference), HttpStatus.OK);
    }

    @GetMapping("/{walletReference}/{reference}")
    public ResponseEntity<?> findByWalletReferenceAndReference(@PathVariable Long walletReference, @PathVariable Long reference) {
        return new ResponseEntity<>(this.transactionService.findByWalletReferenceAndReference(walletReference, reference), HttpStatus.OK);
    }

    @PostMapping("/{walletReference}")
    public ResponseEntity<?> create(@PathVariable Long walletReference, @RequestBody Transaction transaction, BindingResult bindingResult) {
        ResponseEntity responseEntityErrors = this.handleErrorService.validate(bindingResult);
        if(responseEntityErrors != null) {
            return responseEntityErrors;
        }
        Transaction transactionCreateOrUpdate = this.transactionService.walletCreateOrUpdate(walletReference, transaction);
        return new ResponseEntity<Transaction>(transactionCreateOrUpdate, HttpStatus.CREATED);
    }

    @PutMapping("/{walletReference}/{reference}")
    public ResponseEntity<?> update(@PathVariable Long walletReference, @PathVariable Long reference, @RequestBody Transaction transaction, BindingResult bindingResult) {
        ResponseEntity responseEntityErrors = this.handleErrorService.validate(bindingResult);
        if(responseEntityErrors != null) {
            return responseEntityErrors;
        }
        transaction.setReference(reference);
        Transaction transactionUpdate = this.transactionService.walletCreateOrUpdate(walletReference, transaction);
        return new ResponseEntity<Transaction>(transactionUpdate, HttpStatus.OK);

    }

}