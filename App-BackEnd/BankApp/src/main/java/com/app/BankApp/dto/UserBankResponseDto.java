package com.app.BankApp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class UserBankResponseDto {

    private String id;

    private String names;

    private String userName;

    private String email;

    private String phone;

    private Date createdDate;

}
