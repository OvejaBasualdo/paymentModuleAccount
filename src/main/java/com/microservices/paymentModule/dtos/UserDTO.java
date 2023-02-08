package com.microservices.paymentModule.dtos;

import java.util.Set;

public class UserDTO {

    private Long id;
    private String name;
    private String lastName;
    private String dni;
    private String email;
    private String password;
    private Set<AccountDTO> accounts;
}
