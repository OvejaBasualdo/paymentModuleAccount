package com.microservices.paymentModule.controller;

import com.microservices.paymentModule.entity.Account;
import com.microservices.paymentModule.model.User;
import com.microservices.paymentModule.repository.AccountRepository;
import com.microservices.paymentModule.service.IAccountService;
import com.microservices.paymentModule.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/list")
    public List<Account> getListAccounts() {
        return accountService.findAll();
    }

    @GetMapping("/id/{id}")
    public Account getById(@PathVariable Long idAccount) {
        return accountService.findById(idAccount);
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
        cbu = cbu.trim();
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
    public ResponseEntity<Object> createAccount(@RequestBody User user) {
        Account account = accountService.createAccount(user);
        if (account == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @PutMapping("/deleteAccount/{idAccount}")
    public ResponseEntity<Object> deleteAccount(@RequestBody User user, @PathVariable Long idAccount) throws Exception {
        try {
            accountService.deleteByIdAccountAndIdUser(user, idAccount);
            return new ResponseEntity<Object>("Account/s deleted", HttpStatus.OK);
        } catch (Exception e) {
            e.fillInStackTrace();
            return new ResponseEntity<Object>("Account/s not deleted", HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/deleteUserAccounts")
    public void deleteUserAccounts(@RequestBody User user) throws Exception {
        try {
            accountService.deleteAllUserAccounts(user);
        } catch (Exception e) {
            e.fillInStackTrace();
            throw new Exception(e.getMessage());
        }
    }

}
