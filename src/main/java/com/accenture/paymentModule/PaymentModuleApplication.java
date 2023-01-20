package com.accenture.paymentModule;

import com.accenture.paymentModule.model.Account;
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

			Account account = new Account("4871321245", "8572091213111198403212", new BigDecimal(0.00), LocalDateTime.now());
			accountRepository.save(account);
			Account account1 = new Account("4871987612", "0009611273173490542785", new BigDecimal(0.00),LocalDateTime.now());
			accountRepository.save(account1);
		};
	}
}
