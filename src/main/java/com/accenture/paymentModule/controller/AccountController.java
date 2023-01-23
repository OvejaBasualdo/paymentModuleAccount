package com.accenture.paymentModule.controller;

import com.accenture.paymentModule.entity.Account;
import com.accenture.paymentModule.model.User;
import com.accenture.paymentModule.repository.AccountRepository;
import com.accenture.paymentModule.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Account getByCbu(@PathVariable String cbu) {
        return accountService.findByCbu(cbu);
    }

    @PostMapping("/createAccount")
    public Account createAccount(@RequestBody User user) {
        Account account = new Account(user.getId());
        return accountRepository.save(account);
        //return new ResponseEntity<>("Account created", HttpStatus.CREATED);
    }

}
