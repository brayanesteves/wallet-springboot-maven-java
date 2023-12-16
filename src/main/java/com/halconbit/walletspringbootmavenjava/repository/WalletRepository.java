package com.halconbit.walletspringbootmavenjava.repository;

import com.halconbit.walletspringbootmavenjava.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByReference(Long reference);

}