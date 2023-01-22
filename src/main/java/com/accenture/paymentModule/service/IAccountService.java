package com.accenture.paymentModule.service;

import com.accenture.paymentModule.entity.Account;

import java.util.List;

public interface IAccountService {

    public List<Account> findAll();
    public Account findById(Long id);
    public Account findByAccountNumber(String accountNumber);
    public Account findByCbu(String cbu);

    public List<Account> findByUserId(Long id);
}
