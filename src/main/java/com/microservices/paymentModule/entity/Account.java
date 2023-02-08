package com.microservices.paymentModule.entity;

import com.microservices.paymentModule.utils.AccountUtils;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @Column(unique = true)
    private String accountNumber;
    @Column(unique = true)
    private String cbu;
    private BigDecimal balance;
    private LocalDateTime creationDate;
    private Boolean isActive;
    private Long userId;

    public Account() {
    }

    public Long getUserId() {
        return userId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Account(Long userId) {
        this.accountNumber = AccountUtils.generateRandomDigits(10);
        this.cbu = AccountUtils.generateRandomDigits(22);
        this.balance = new BigDecimal(0.00);
        this.creationDate = LocalDateTime.now();
        this.isActive = true;
        this.userId = userId;
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
