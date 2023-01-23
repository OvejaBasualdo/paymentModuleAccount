package com.accenture.paymentModule.model;

import com.accenture.paymentModule.entity.Account;

import java.util.HashSet;
import java.util.Set;


public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private String password;
    private Set<Account> accounts = new HashSet<>();
    private Set<Long> accountId=new HashSet<>();

    public Set<Long> getAccountId() {
        return accountId;
    }

    public void setAccountId(Set<Long> accountId) {
        this.accountId = accountId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
