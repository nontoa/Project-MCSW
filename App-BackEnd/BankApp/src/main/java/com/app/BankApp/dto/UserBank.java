package com.app.BankApp.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class UserBank {

    @NonNull
    private String id;

    @NonNull
    private String names;

    @NonNull
    private String userName;

    @NonNull
    private String password;

    @NonNull
    private String email;

    private String phone;

}
