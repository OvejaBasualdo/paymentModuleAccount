package com.microservices.paymentModule.service;

import com.microservices.paymentModule.entity.Account;
import com.microservices.paymentModule.exceptions.ElementNotFoundException;
import com.microservices.paymentModule.exceptions.FoundsException;
import com.microservices.paymentModule.model.User;
import com.microservices.paymentModule.repository.AccountRepository;
import com.microservices.paymentModule.utils.AccountUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private RestTemplate userRest;
    @Autowired
    private AccountRepository accountRepository;

    private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Override
    public List<Account> findAll() {
        List<Account> accounts = accountRepository.findByIsActiveTrue();
        return accounts;
    }

    @Override
    public Account findById(Long id) {
        Account account = accountRepository.findByIdAndIsActiveTrue(id).orElse(null);
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
        return accountRepository.findByUserIdAndIsActiveTrue(userId);
    }


    @Override
    public Account createAccount(User user) {
        logger.info("Starting with process: CREATE ACCOUNT");
        Account account;
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", user.getId().toString());
        try {
            logger.info("Starting with process: getting a user");
            User userTest = userRest.getForObject("http://localhost:8001/api/users/{id}", User.class, pathVariables);
            do {
                logger.info("Setting the account to user");
                account = new Account(user.getId());
            } while (accountRepository.findByAccountNumber(account.getAccountNumber()) != null
                    || accountRepository.findByCbu(account.getCbu()) != null);
            logger.info("Account created");
            return accountRepository.save(account);
        } catch (Exception e) {
            logger.warn("Account not created");
            return null;
        }
    }

    @Override
    public void deleteByIdAccountAndIdUser(User user, Long accountId) throws Exception {
        List<Long> accountsIdFromUser = accountRepository.findIdByUserIdAndIsActiveTrue(user.getId());
        logger.info("Starting with process: DELETE THE ACCOUNT " + accountId + "TO USER ID " + user.getId());
        Account accountToDelete = accountRepository.findByIdAndIsActiveTrue(accountId)
                .orElse(null);
        logger.info("getting the account from repository");
        if (accountToDelete == null) {
            logger.warn("ACCOUNT NOT FOUND");
            throw new ElementNotFoundException("Account not found");
        }
        if (AccountUtils.foundZeroVerification(accountToDelete)) {
            logger.warn("ACCOUNT WITH MONEY, WE CAN'T DELETE");
            throw new FoundsException("Account not found");
        }
        if (AccountUtils.verificationUser(accountToDelete, user.getId())) {
            logger.warn("ACCOUNT ID:" + accountId + " NOT BELONGS TO USER ID:" + user.getId());
        }
        logger.info("the account " + accountId + " now is INACTIVE ");
        accountToDelete.setIsActive(Boolean.FALSE);
        accountRepository.save(accountToDelete);
    }

    @Override
    public void deleteAllUserAccounts(User user) throws Exception {
        List<Long> accountsIdFromUser = accountRepository.findIdByUserIdAndIsActiveTrue(user.getId());
        List<Long> accountsIdNotDeleted = null;
        for (Long aux : accountsIdFromUser) {
            logger.info("Starting with process: DELETE THE ACCOUNT " + aux + "TO USER ID " + user.getId());
            Account accountToDelete = accountRepository.findByIdAndIsActiveTrue(aux).orElse(null);
            if (AccountUtils.foundZeroVerification(accountToDelete)) {
                logger.warn("ACCOUNT WITH MONEY, WE CAN'T DELETE THE ACCOUNT WITH ID " + aux);
                accountsIdNotDeleted.add(aux);
            } else {
                logger.info("the account " + aux + " now is INACTIVE ");
                accountToDelete.setIsActive(Boolean.FALSE);
                accountRepository.save(accountToDelete);
            }
        }
        if (accountsIdNotDeleted != null) {
            throw new FoundsException("Account not found");
        }
    }
}


