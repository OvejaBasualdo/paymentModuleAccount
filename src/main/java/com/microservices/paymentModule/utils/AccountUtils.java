package com.microservices.paymentModule.utils;

import com.microservices.paymentModule.entity.Account;

import java.math.BigDecimal;

public final class AccountUtils {
    public static String generateRandomDigits(Integer limit) {
        String randomNumber = "";
        for (int i = 0; i < limit; i++) {
            int newNumber = (int) (Math.random() * 10);
            randomNumber += String.valueOf(newNumber);
        }
        return randomNumber;
    }

    public static Boolean verifyNumber(String number) {
        try {
            Double a = Double.parseDouble(number);
            System.out.printf(String.valueOf(a));
            System.out.printf("Integer " + a);
            return true;
        } catch (NumberFormatException e) {
            e.getMessage();
            return false;
        }
    }

    public static Boolean foundZeroVerification(Account account) {
        if (account.getBalance() == BigDecimal.ZERO) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean balanceVerification(BigDecimal value, BigDecimal balance) {
        int a = value.compareTo(balance);
        if (a > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean verificationUser(Account account, Long idUser) {
        if (account.getUserId() != idUser) {
            return true;
        } else {
            return false;
        }
    }


}
