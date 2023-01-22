package com.accenture.paymentModule.controller;

import com.accenture.paymentModule.entity.Account;
import com.accenture.paymentModule.repository.AccountRepository;
import com.accenture.paymentModule.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountServiceImpl accountService;
    @Autowired
    private AccountRepository accountRepository;

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

    @PostMapping("/createFirstAccount")
    public ResponseEntity<Object>createFirstAccount(){
        Account account= new Account();
        accountRepository.save(account);
        return new ResponseEntity<>("Account created", HttpStatus.CREATED);
    }

}
