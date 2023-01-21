package com.accenture.paymentModule;

import com.accenture.paymentModule.entity.Account;
import com.accenture.paymentModule.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
public class PaymentModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentModuleApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(AccountRepository accountRepository) {
        return (args) -> {
            Account account = new Account(new BigDecimal(0.00), LocalDateTime.now());
            accountRepository.save(account);
            Account account1 = new Account(new BigDecimal(0.00), LocalDateTime.now());
            accountRepository.save(account1);
        };
    }
}
