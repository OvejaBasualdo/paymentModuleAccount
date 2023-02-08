package com.microservices.paymentModule.repository;

import com.microservices.paymentModule.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByAccountNumber(String accountNumber);
    Optional<Account> findByIdAndIsActiveTrue(Long accountId);
    Account findByCbu(String cbu);
    List<Account> findByIsActiveTrue();
    List<Account> findByUserIdAndIsActiveTrue(Long userId);
    @Query("select a.id from Account a where a.userId = :userId")
    List<Long> findIdByUserIdAndIsActiveTrue(@Param("userId")Long userId);

}
