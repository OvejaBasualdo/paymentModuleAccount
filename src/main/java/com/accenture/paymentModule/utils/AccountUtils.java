package com.accenture.paymentModule.utils;

public final class AccountUtils {
    public static String generateRandomDigits(Integer limit) {
        String randomNumber = "";
        for (int i = 0; i < limit; i++) {
            int newNumber = (int) (Math.random() * 10);
            randomNumber += String.valueOf(newNumber);
        }
        return randomNumber;
    }

}
