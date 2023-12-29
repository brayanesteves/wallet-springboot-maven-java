package com.halconbit.walletspringbootmavenjava.repository;

import com.halconbit.walletspringbootmavenjava.entity.Transaction;
import com.halconbit.walletspringbootmavenjava.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByReference(Long reference);
    List<Transaction> findByWallet(Wallet wallet);

}