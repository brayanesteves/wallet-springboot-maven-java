package com.halconbit.walletspringbootmavenjava.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="0_Wllt")
public class Wallet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Rfrnc")
    private Long reference;

    @Column(name="Nm")
    private String name;

    @Column(name="AccntNmbr")
    private String accountManager;

    @Column(name="Dscrptn")
    private String description;

    @Column(name="Prrty")
    private Integer priority; // 1=High; 2=Medium; 3=Low

    @Column(name="CrrntBlnc")
    private Double currentBalance;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "wallet", orphanRemoval = true)
    private List<Transaction> transactionList;

    @PrePersist
    public void setBalance() {
        this.currentBalance = Double.valueOf(0);
    }
}