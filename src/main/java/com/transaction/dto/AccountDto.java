package com.transaction.dto;

import lombok.Data;

@Data
public class AccountDto {

    private String from;
    private String to;
    private Double balance;
}
