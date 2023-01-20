package com.accenture.paymentModule.controller;

import com.accenture.paymentModule.model.Account;
import com.accenture.paymentModule.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @GetMapping("/list")
    public List<Account> getListAccounts() {
        return accountService.findAll();
    }

    @GetMapping("/list/id/{id}")
    public Account getById(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @GetMapping("/list/accountNumber/{accountNumber}")
    public Account getByAccountNumber(@PathVariable String accountNumber) {
        return accountService.findByAccountNumber(accountNumber);
    }

    @GetMapping("/list/cbu/{cbu}")
    public Account getById(@PathVariable String cbu) {
        return accountService.findByCbu(cbu);
    }

}
