package com.app.BankApp.dto;

import com.app.BankApp.dto.constants.UserRol;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ValidateUserResponseDto {

    private String authentication;
    private UserRol rol;
    private String accountId;
    private String userId;


    // código añadido de front
    @JsonCreator
    public ValidateUserResponseDto(@JsonProperty("authentication") String authentication,
                                   @JsonProperty("rol") UserRol rol,
                                   @JsonProperty("accountId") String accountId,
                                   @JsonProperty("accountId") String userId) {

        this.authentication = authentication;
        this.rol = rol;
        this.accountId = accountId;
        this.userId = userId;
    }


}
