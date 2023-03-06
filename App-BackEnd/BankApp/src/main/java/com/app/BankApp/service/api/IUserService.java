package com.app.BankApp.service.api;

import com.app.BankApp.dto.UserBank;
import com.app.BankApp.dto.UserBankResponseDto;
import com.app.BankApp.dto.ValidateUserDto;

import java.util.List;

public interface IUserService {

    String isValidUser(ValidateUserDto user);

    void createUser(UserBank user);

    String getTotalBalance(String userId);

    List<UserBankResponseDto> getAllUsers();
}
