package com.app.BankApp.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ValidateUserResponseDto {

    private String authentication;
    private String rol;
    

    
    
}
