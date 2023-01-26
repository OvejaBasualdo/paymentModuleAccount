package com.accenture.paymentModule.controller;

import com.accenture.paymentModule.entity.Account;
import com.accenture.paymentModule.model.User;
import com.accenture.paymentModule.repository.AccountRepository;
import com.accenture.paymentModule.service.AccountServiceImpl;
import com.accenture.paymentModule.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list/userId/{userId}")
    public List<Account> getByUserId(@PathVariable Long userId) {
        return accountService.findByUserId(userId);
    }

    @GetMapping("/list/accountNumber/{accountNumber}")
    public Account getByAccountNumber(@PathVariable String accountNumber) {
        return accountService.findByAccountNumber(accountNumber);
    }

    @GetMapping("/list/cbu/{cbu}")
    public ResponseEntity<Object> getByCbu(@PathVariable String cbu) {
        if (cbu.length() != 22) {
            return new ResponseEntity<>("CBU must have 22 digits", HttpStatus.NOT_FOUND);
        }
        if (AccountUtils.verifyNumber(cbu) == false) {
            return new ResponseEntity<>("CBU only accept digits numbers", HttpStatus.NOT_FOUND);
        }
        Account account = accountService.findByCbu(cbu);
        return new ResponseEntity<>(account, HttpStatus.ACCEPTED);
    }

    @PostMapping("/createAccount")
    public Account createAccount(@RequestBody User user) {
        Account account;
        do {
            account = new Account(user.getId());
        } while (accountRepository.findByAccountNumber(account.getAccountNumber()) != null
                || accountRepository.findByCbu(account.getCbu()) != null);
        return accountRepository.save(account);
        //return new ResponseEntity<>("Account created", HttpStatus.CREATED);
    }

}
