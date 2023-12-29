package com.halconbit.walletspringbootmavenjava.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="0_Trnsctn")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Rfrnc")
    private Long reference;

    @Column(name="Amnt")
    private Double amount;

    @Column(name="Dscrptn")
    private String description;

    @Column(name="Typ")
    private int type; // 1 for Income, 2 for Expense, 3 for Transfer.

    @Column(name="TrnsctnDt")
    private Date transactionDate;
}
