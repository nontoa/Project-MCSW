package com.app.BankApp.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class ValidateUserDto {

    @NonNull
    private String userName;

    @NonNull
    private String password;

}
