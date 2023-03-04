package com.app.BankApp.service.api;

import com.app.BankApp.dto.UserBank;
import com.app.BankApp.dto.ValidateUserDto;

public interface IUserService {

    Boolean isValidUser(ValidateUserDto user);

    void createUser(UserBank user);

    String getTotalBalance(String userId);
}
