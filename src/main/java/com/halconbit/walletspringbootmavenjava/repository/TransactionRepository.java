package com.halconbit.walletspringbootmavenjava.repository;

import com.halconbit.walletspringbootmavenjava.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
