package com.halconbit.walletspringbootmavenjava.services;

import com.halconbit.walletspringbootmavenjava.entity.Transaction;
import com.halconbit.walletspringbootmavenjava.entity.Wallet;
import com.halconbit.walletspringbootmavenjava.exception.TransactionException;
import com.halconbit.walletspringbootmavenjava.repository.TransactionRepository;
import com.halconbit.walletspringbootmavenjava.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletRepository walletRepository;

    public List<Transaction> findAll() {
        return this.transactionRepository.findAll();
    }

    public List<Transaction> findAllByWallet(Long walletReference) {
        Optional<Wallet> walletOptional = this.walletRepository.findByReference(walletReference);
        if(walletOptional.isPresent()) {
            return this.transactionRepository.findByWallet(walletOptional.get());
        }
        return null;
    }

    public Transaction findByReference(Long reference) {
        Optional<Transaction> transactionOptional = this.transactionRepository.findByReference(reference);
        if(this.transactionRepository.findByReference(reference).isPresent()) {
            return transactionOptional.get();
        }
        // TODO
        throw new TransactionException("Transaction with " + reference + " does not exists!");
    }

    public Transaction findByWalletReferenceAndReference(Long walletReference, Long reference) {
        Optional<Wallet> wallet =  this.walletRepository.findByReference(walletReference);
        if(wallet.isPresent()) {
            Optional<Transaction> transactionOptional = this.transactionRepository.findByReference(reference);
            if (this.transactionRepository.findByReference(reference).isPresent()) {
                return transactionOptional.get();
            }
        }
        // TODO
        throw new TransactionException("Transaction with " + reference + " does not exists!");
    }

    public Transaction transactionCreateOrUpdate(Transaction transaction) {
        if(transaction.getReference() == null) {
            this.transactionRepository.save(transaction);
        } else {
            this.transactionRepository.save(transaction);
        }
        return transaction;
    }

    public Transaction walletCreateOrUpdate(Long walletReference, Transaction transaction) {
       Optional<Wallet> wallet =  this.walletRepository.findByReference(walletReference);
       if(wallet.isPresent()) {
           transaction.setWallet(wallet.get());
           this.transactionRepository.save(transaction);
           return transaction;
       }
       return null;
    }

    public boolean delete(Long reference) {
        Optional<Transaction> walletOptional = this.transactionRepository.findByReference(reference);
        if(this.transactionRepository.findByReference(reference).isPresent()) {
            this.transactionRepository.delete(walletOptional.get());
            return true;
        }
        // TODO
        throw new TransactionException("Transaction with " + reference + " does not exists!");
    }

    public boolean deleteByWalletReferenceAndReference(Long walletReference, Long reference) {
        Optional<Wallet> wallet =  this.walletRepository.findByReference(walletReference);
        if(wallet.isPresent()) {
            Optional<Transaction> walletOptional = this.transactionRepository.findByReference(reference);
            if (this.transactionRepository.findByReference(reference).isPresent()) {
                this.transactionRepository.delete(walletOptional.get());
                return true;
            }
        }
        // TODO
        throw new TransactionException("Transaction with " + reference + " does not exists!");
    }

}