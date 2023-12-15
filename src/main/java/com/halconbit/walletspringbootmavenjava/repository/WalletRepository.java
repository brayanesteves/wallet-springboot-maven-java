package com.halconbit.walletspringbootmavenjava.repository;

import com.halconbit.walletspringbootmavenjava.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
}