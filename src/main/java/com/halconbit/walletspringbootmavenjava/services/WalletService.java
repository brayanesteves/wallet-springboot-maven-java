package com.halconbit.walletspringbootmavenjava.services;

import com.halconbit.walletspringbootmavenjava.entity.Wallet;
import com.halconbit.walletspringbootmavenjava.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public Wallet createOrUpdate(Wallet wallet) {
        if(wallet.getReference() == null) {
            this.walletRepository.save(wallet);
        } else {
            this.walletRepository.save(wallet);
        }
        return wallet;
    }

}