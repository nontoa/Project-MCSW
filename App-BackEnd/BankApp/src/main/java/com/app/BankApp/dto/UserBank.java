package com.app.BankApp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    
    
    // código añadido de front 
    @JsonCreator
    public UserBank(@JsonProperty("id") String id,
            @JsonProperty("names") String names,
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("email") String email,
            @JsonProperty("phone") String phone) {
        
        this.id = id;
        this.names = names;
        this.userName = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

}


