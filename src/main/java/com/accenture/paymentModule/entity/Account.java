package com.accenture.paymentModule.entity;

import com.accenture.paymentModule.model.User;
import com.accenture.paymentModule.utils.AccountUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String accountNumber;
    private String cbu;
    private BigDecimal balance;
    private LocalDateTime creationDate;

    private Long userId;


    public Account() {
        this.accountNumber = AccountUtils.generateRandomDigits(10);
        this.cbu = AccountUtils.generateRandomDigits(22);
        this.balance = new BigDecimal(0.00);
        this.creationDate = LocalDateTime.now();
    }

    public Account(Long id, BigDecimal balance, LocalDateTime creationDate) {
        this.accountNumber = AccountUtils.generateRandomDigits(10);
        this.cbu = AccountUtils.generateRandomDigits(22);
        this.balance = balance;
        this.creationDate = creationDate;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }


}
