package com.accenture.paymentModule.service;

import com.accenture.paymentModule.entity.Account;
import com.accenture.paymentModule.model.User;
import com.accenture.paymentModule.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private RestTemplate userRest;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        List<Account> accounts = accountRepository.findAll();
        return accounts;
    }

    @Override
    public Account findById(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        return account;
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public Account findByCbu(String cbu) {
        return accountRepository.findByCbu(cbu);
    }

    @Override
    public List<Account> findByUserId(Long userId) {
        return accountRepository.findByUserId(userId);
    }

    @Override
    public User findUserById(Long id) {
        User user = userRest.getForObject("http://localhost:8001/api/users/list/{id}", User.class);
        return user;
    }
}
