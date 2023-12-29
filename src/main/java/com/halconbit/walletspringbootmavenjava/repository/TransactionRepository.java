package com.halconbit.walletspringbootmavenjava.repository;

import com.halconbit.walletspringbootmavenjava.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
