package com.halconbit.walletspringbootmavenjava.services;

import com.halconbit.walletspringbootmavenjava.entity.Wallet;
import com.halconbit.walletspringbootmavenjava.exception.WalletException;
import com.halconbit.walletspringbootmavenjava.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public List<Wallet> findAll() {
        return this.walletRepository.findAll();
    }

    public List<Wallet> findAllByOrderByPriority() {
        return this.walletRepository.findAllByOrderByPriority();
    }

    public Wallet findByReference(Long reference) {
        Optional<Wallet> walletOptional = this.walletRepository.findByReference(reference);
        if(this.walletRepository.findByReference(reference).isPresent()) {
            return walletOptional.get();
        }
        // TODO
        throw  new WalletException("Wallet with " + reference + " dows not exists!");
    }

    public Wallet createOrUpdate(Wallet wallet) {
        if(wallet.getReference() == null) {
            this.walletRepository.save(wallet);
        } else {
            this.walletRepository.save(wallet);
        }
        return wallet;
    }

    public boolean delete(Long reference) {
        Optional<Wallet> walletOptional = this.walletRepository.findByReference(reference);
        if(this.walletRepository.findByReference(reference).isPresent()) {
            this.walletRepository.delete(walletOptional.get());
            return true;
        }
        // TODO
        throw  new WalletException("Wallet with " + reference + " dows not exists!");
    }

}