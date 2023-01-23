package com.accenture.paymentModule.repository;

import com.accenture.paymentModule.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByAccountNumber(String accountNumber);

    Account findByCbu(String cbu);

    List<Account> findByUserId(Long userId);
}
