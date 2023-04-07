package com.app.BankApp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    
    
//código agregado al back    
@JsonCreator
    public ValidateUserDto(@JsonProperty("username") String username,
                           @JsonProperty("password") String password) {
        this.userName = username;
        this.password = password;
    }
    

}
