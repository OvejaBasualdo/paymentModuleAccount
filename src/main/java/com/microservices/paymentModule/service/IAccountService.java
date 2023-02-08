package com.microservices.paymentModule.service;

import com.microservices.paymentModule.entity.Account;
import com.microservices.paymentModule.model.User;

import java.util.List;

public interface IAccountService {

    public List<Account> findAll();
    public Account findById(Long id);
    public Account findByAccountNumber(String accountNumber);
    public Account findByCbu(String cbu);
    public List<Account> findByUserId(Long userId);
    public void deleteByIdAccountAndIdUser(User user, Long idAccount) throws Exception;
    public void deleteAllUserAccounts(User user) throws Exception;
    public Account createAccount(User user);

}
