package com.app.BankApp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ValidateUserResponseDto {

    private String authentication;
    private String rol;
    private String accountId;


    // código añadido de front
    @JsonCreator
    public ValidateUserResponseDto(@JsonProperty("authentication") String authentication,
                             @JsonProperty("rol") String rol,
                             @JsonProperty("accountId") String accountId){

        this.authentication = authentication;
        this.rol = rol;
        this.accountId = accountId;
    }
    
    
}
